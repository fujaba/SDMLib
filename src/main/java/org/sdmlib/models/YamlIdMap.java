package org.sdmlib.models;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.AbstractArray;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;

public class YamlIdMap
{

   private ArrayList<String> packageNames;
   private Scanner scanner;
   private String yaml;
   private String currentToken;
   private String lookAheadToken;
   private int currentPos;
   private int lookAheadPos;

   public YamlIdMap(String... packageNames)
   {
      Objects.requireNonNull(packageNames);
      List<String> list = Arrays.asList(packageNames);
      this.packageNames = new ArrayList<String>(list);
      creatorMap = new CreatorMap(this.packageNames);
   }


   public Object decodeCSV(String fileName)
   {
      try
      {
         byte[] bytes = Files.readAllBytes(Paths.get(fileName));

         String csvText = new String(bytes);

         String yamlText = convertCsv2Yaml(csvText);

         // System.out.println(yamlText);

         return this.decode(yamlText);

      } catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }

      return null;
   }

   private String convertCsv2Yaml(String csvText)
   {
      String[] split = csvText.split(";");

      for (int i = 0; i < split.length; i++)
      {
         String token = split[i];


         if (token.startsWith("\"") && token.endsWith("\""))

         {
            // already done
            continue;
         }

         if (token.startsWith("\"") && !token.endsWith("\""))
         {
            // there is a semicolon within "   ;   " , recombine it
            int j = i;
            String nextToken;
            while (true)
            {
               j++;
               nextToken = split[j];
               split[j] = "";
               token = token + ";" + nextToken;
               if (nextToken.endsWith("\""))
               {
                  split[i] = token;
                  i = j;
                  break;
               }
            }
            continue;
         }

         if (token.trim().length() == 0)
         {
            continue;
         }

         Pattern pattern = Pattern.compile("\\s");
         Matcher matcher = pattern.matcher(token.trim());
         boolean found = matcher.find();

         if (found)
         {
            token = encapsulate(token);
            split[i] = token;
         }
      }

      StringBuilder buf = new StringBuilder();

      for (int i = 0; i < split.length; i++)
      {
         buf.append(split[i]).append(" ");
      }

      return buf.toString();
   }


   public Object decode(String yaml, Object root)
   {
      String className = root.getClass().getSimpleName();
      
      String key = className.substring(0, 1).toLowerCase();
      
      int num = objIdMap.size() + 1;
      
      key += num;
      
      objIdMap.put(key, root);
      
      decode(yaml);
      
      return root;
   }
   
   /**
    * Object decode(String yaml)
    * Decode string and create typed object structure.
    * 
    * yaml grammar
    * yaml ::= objects*
    * object ::= plainObject | objectList
    * plainObject ::= - objId': ' type\n attr*
    * attr ::= attrName': ' attrValue\n 
    * attrValue ::= id | string | '[' attrValue * ']'
    * objectList ::= type colName:* \n key: attrValue* \n*
    * valueRow ::= attrValue* \n
    * 
    * @param yaml string describing object structure
    * @return first object
    * @see <a href='(StudyRightWithAssignmentsStoryboards.java:122)'>StudyRightWithAssignmentsStoryboards.java:122</a>
    */
   public Object decode(String yaml)
   {
      this.yaml = yaml;
      Object root = null;
      
      scanner = new Scanner(yaml);
      nextToken();
      nextToken();

      root = parseObjectIds();
      
      scanner = new Scanner(yaml);
      nextToken();
      nextToken();

      parseObjectAttrs();
      
      return root;
   }

   private void parseObjectAttrs()
   {
      while ( ! "".equals(currentToken))
      {
         if ( ! "-".equals(currentToken) )
         {
            printError("'-' expected");
            nextToken();
            continue;
         }

         String key = nextToken();
         
         if ( key.endsWith(":"))
         {
            // usual
            parseUsualObjectAttrs();
            continue;
         }
         else
         {
            parseObjectTableAttrs();
            continue;
         }
      }

   }

   private void parseObjectTableAttrs()
   {
      // skip column names
      String className = currentToken;
      
      SendableEntityCreator creator = getCreator(className);
      nextToken();
      
      ArrayList<String> colNameList = new ArrayList<String>();

      while ( ! "".equals(currentToken) && lookAheadToken.endsWith(":"))
      {
         String colName = stripColon(currentToken);
         colNameList.add(colName);
         nextToken();
      }
      
      while ( ! "".equals(currentToken) && ! "-".equals(currentToken))
      {
         String objectId = stripColon(currentToken);
         nextToken();

         Object obj = objIdMap.get(objectId);
         
         // column values
         int colNum = 0;
         while ( ! "".equals(currentToken) && ! currentToken.endsWith(":") && ! "-".equals(currentToken))
         {
            String attrName = colNameList.get(colNum);
            
            if (currentToken.startsWith("["))
            {
               String value = currentToken.substring(1);
               if (value.trim().equals(""))
               {
                  value = nextToken();
               }
               setValue(creator, obj, attrName, value);
               
               while (! "".equals(currentToken) && ! currentToken.endsWith("]") )
               {
                  nextToken();
                  value = currentToken;
                  if (currentToken.endsWith("]"))
                  {
                     value = currentToken.substring(0, currentToken.length()-1);
                  }
                  if ( ! value.trim().equals(""))
                  {
                     setValue(creator, obj, attrName, value);
                  }
               }
            }
            else
            {
               setValue(creator, obj, attrName, currentToken);
            }
            colNum++;
            nextToken();
         }
      }
   }

   private void parseUsualObjectAttrs()
   {
      String objectId = stripColon(currentToken);
      String className = nextToken();
      nextToken();

      SendableEntityCreator creator = getCreator(className);
      
      Object obj = objIdMap.get(objectId);
      
      // read attributes
      while ( ! currentToken.equals("") && ! currentToken.equals("-"))
      {
         String attrName = stripColon(currentToken);
         nextToken();
         // many values
         while ( ! currentToken.equals("") 
               && ! currentToken.endsWith(":")
               && ! currentToken.equals("-"))
         {
            String attrValue = currentToken;
            
            setValue(creator, obj, attrName, attrValue);
            
            nextToken();
         }
      }
   }

   private void setValue(SendableEntityCreator creator, Object obj, String attrName, String attrValue)
   {
      String type = "new";

      if (attrName.endsWith(".remove"))
      {
         attrName = attrName.substring(0, attrName.length() - ".remove".length());

         if (creator.getValue(obj, attrName) instanceof Collection)
         {
            type = SendableEntityCreator.REMOVE;
         }
         else
         {
            attrValue = null;
         }
      }

      try
      {
         creator.setValue(obj, attrName, attrValue, type);
      }
      catch (Exception e)
      {
         // maybe a node
         Object targetObj = objIdMap.get(attrValue);
         if (targetObj != null)
         {
            creator.setValue(obj, attrName, targetObj, type);
         }
      }
   }

   private String nextToken()
   {
      currentToken = lookAheadToken;
      currentPos = lookAheadPos;
      
      if (scanner.hasNext())
      {
         
         lookAheadToken = scanner.next();
         lookAheadPos = scanner.match().start();
      }
      else
      {
         lookAheadToken = "";
      }
      
      
      
      if (lookAheadToken.startsWith("\""))
      {
         // get up to end of string
         int stringStartPos = lookAheadPos + 1;
         String subToken = lookAheadToken;
         MatchResult match = scanner.match();
         int subTokenEnd = match.end() - 1;
         while ( subTokenEnd < stringStartPos || ( ! subToken.endsWith("\"") || subToken.endsWith("\\\"")) 
               && scanner.hasNext())
         {
            subToken = scanner.next();
            subTokenEnd = scanner.match().end() - 1;
         }
         
         lookAheadToken = yaml.substring(stringStartPos, subTokenEnd);
         
         lookAheadToken = deEncapsulate(lookAheadToken);
      }
      
      return currentToken;
   }

   private Object parseObjectIds()
   {
      Object root = null;
      while ( ! "".equals(currentToken))
      {
         if ( ! "-".equals(currentToken) )
         {
            printError("'-' expected");
            nextToken();
            continue;
         }

         String key = nextToken();
         
         if ( key.endsWith(":"))
         {
            // usual
            Object now = parseUsualObjectId();
            if (root == null) root = now;
            continue;
         }
         else
         {
            Object now = parseObjectTableIds();
            if (root == null) root = now;
            continue;
         }
      }

      return root;
   }
   
   private SimpleKeyValueList<String, Object> objIdMap = new SimpleKeyValueList<String, Object>().withFlag(AbstractArray.BIDI);

   private Object parseUsualObjectId()
   {
      String objectId = stripColon(currentToken);
      String className = nextToken();
      
      Object obj = objIdMap.get(objectId);
      
      if (obj == null)
      {
         SendableEntityCreator creator = getCreator(className);

         obj = creator.getSendableInstance(false);

         objIdMap.put(objectId, obj);
      }
      
      // skip attributes
      while ( ! currentToken.equals("") && ! currentToken.equals("-"))
      {
         nextToken();
      }
      
      return obj;
   }

   private Object parseObjectTableIds()
   {
      Object root = null;
      
      // skip column names
      String className = currentToken;
      
      SendableEntityCreator creator = getCreator(className);

      while ( ! "".equals(currentToken) && lookAheadToken.endsWith(":"))
      {
         nextToken();
      }
      
      while ( ! "".equals(currentToken) && ! "-".equals(currentToken))
      {
         String objectId = stripColon(currentToken);
         nextToken();

         Object obj = creator.getSendableInstance(false);
         
         objIdMap.put(objectId, obj);
         
         if (root == null) root = obj;
         
         // skip column values
         while (! "".equals(currentToken) && ! currentToken.endsWith(":") && ! "-".equals(currentToken))
         {
            nextToken();
         }
      }
      
      return root;
   }


   private String stripColon(String key)
   {
      String id = key;

      if (key.endsWith(":"))
      {
         id = key.substring(0, key.length() - 1);
      }
      else
      {
         printError("key does not end with ':' " + key);
      }
      
      return id;
   }

   private void printError(String msg)
   {
      int startPos = currentPos;
      
      if (startPos >=  10)
      { 
         startPos -= 10;
      }
      else
      {
         startPos = 0;
      }
      
      int endPos = currentPos + 20;
      
      if (endPos >=  yaml.length())
      { 
         endPos = yaml.length();
      }

      System.err.println(yaml.substring(startPos, currentPos) + "<--" + msg + "-->" + yaml.substring(currentPos, endPos));
   }

   private Object parseObjList(String key, String second)
   {
      
      return null;
   }


   CreatorMap creatorMap;

   public SendableEntityCreator getCreator(String clazzName)
   {
      return creatorMap.getCreator(clazzName);
   }



   public Object getObject(String objId)
   {
      return objIdMap.get(objId);
   }

   public String encode(Object... rootObjList)
   {
      
      SimpleList<Object> simpleList = new SimpleList<Object>();
      simpleList.add(rootObjList);
      
      StringBuilder buf = new StringBuilder();
      
      // collect objects
      while ( ! simpleList.isEmpty())
      {
         Object obj = simpleList.first();
         simpleList.remove(0);

         if (obj instanceof PropertyChangeEvent)
         {
            PropertyChangeEvent event = (PropertyChangeEvent) obj;

            obj = event.getSource();

            // already known?
            String key = getOrCreateKey(obj);

            String className = obj.getClass().getSimpleName();

            buf.append("- ").append(key).append(": \t").append(className).append("\n");

            String propertyName = event.getPropertyName();

            Object value = event.getNewValue();

            if (value == null)
            {
               value = event.getOldValue();

               propertyName = propertyName + ".remove";
            }

            Class valueClass = value.getClass();

            if (  valueClass.getName().startsWith("java.lang.") || valueClass == String.class)
            {
               buf.append("  ").append(propertyName).append(": \t").append(encapsulate(value.toString())).append("\n");
            }
            else
            {
               // value is an object
               String valueKey = getOrCreateKey(value);

               buf.append("  ").append(propertyName).append(": \t").append(valueKey).append("\n");

               if (event.getNewValue() != null)
               {
                  buf.append("- ").append(valueKey).append(": \t").append(valueClass.getSimpleName()).append("\n");
               }
            }

            return buf.toString();
         }

         // already known? 
         String key = objIdMap.getKey(obj);
         
         if (key == null)
         {
            // add to map
            key = addToObjIdMap(obj);

            String className = obj.getClass().getSimpleName();
            
            // find neighbors
            SendableEntityCreator creator = getCreator(className);
            
            for (String prop : creator.getProperties())
            {
               Object value = creator.getValue(obj, prop);
               
               if (value == null)
               {
                  continue;
               }
               
               Class valueClass = value.getClass();
               
               if (value instanceof Collection)
               {
                  for (Object valueObj : (Collection) value)
                  {
                     simpleList.add(valueObj);
                  }
               }
               else if (  valueClass.getName().startsWith("java.lang.") || valueClass == String.class)
               {
                  continue;
               }
               else
               {
                  simpleList.add(value);
               }
            }
         }
         
      } // collect objects
      
      for ( Entry<String, Object> entry : objIdMap.entrySet())
      {
         String key = entry.getKey();
         Object obj = entry.getValue();
         String className = obj.getClass().getSimpleName();
         
         
         buf.append("- ").append(key).append(": \t").append(className).append("\n");
         
         // attrs
         SendableEntityCreator creator = getCreator(className);
         
         for (String prop : creator.getProperties())
         {
            Object value = creator.getValue(obj, prop);
            
            if (value == null)
            {
               continue;
            }
            
            if (value instanceof Collection)
            {
               if (((Collection) value).isEmpty())
               {
                  continue;
               }
               
               buf.append("  ").append(prop).append(": \t");
               for (Object valueObj : (Collection) value)
               {
                  String valueKey = objIdMap.getKey(valueObj);
                  buf.append(valueKey).append(" \t");
               }
               buf.append("\n");
            }
            else
            {
               String valueKey = objIdMap.getKey(value);
               
               if (valueKey != null)
               {
                  buf.append("  ").append(prop).append(": \t").append(valueKey).append("\n");
               }
               else
               {
                  if (value instanceof String)
                  {
                     value = encapsulate((String) value);
                  }
                  buf.append("  ").append(prop).append(": \t").append(value).append("\n");
               }
            }
         }
         buf.append("\n");
      }
      
      return buf.toString();
   }

   private String getOrCreateKey(Object obj)
   {
      String key = objIdMap.getKey(obj);

      if (key == null)
      {
         key = addToObjIdMap(obj);
      }
      return key;
   }

   private String addToObjIdMap(Object obj)
   {
      String className = obj.getClass().getSimpleName();

      String key = className.substring(0, 1).toLowerCase();

      int num = objIdMap.size() + 1;

      key += num;

      objIdMap.put(key, obj);

      return key;
   }

   private String deEncapsulate(String value)
   {
      value = value.replaceAll("\\\\\"", "\"");
      return value;
   }
   
   private String encapsulate(String value)
   {
      if (value.matches("[a-zA-Z0-9_\\.]+"))
      {
         return value;
      }
      value = value.replaceAll("\"", "\\\\\"");
      return "\"" + value + "\"";
   }

}
