package org.sdmlib.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

import org.sdmlib.CGUtil;
import org.sdmlib.storyboards.GenericCreator;

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
   }

   // yaml grammar
   // yaml ::= objects*
   // object ::= plainObject | objectList
   // plainObject ::= - objId': ' type\n attr*
   // attr ::= attrName': ' attrValue\n 
   // attrValue ::= id | string | '[' attrValue * ']'
   // objectList ::= type colName:* \n key: attrValue* \n*
   // valueRow ::= attrValue* \n
   
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
      try
      {
         creator.setValue(obj, attrName, attrValue, "new");
      }
      catch (Exception e)
      {
         // maybe a node
         Object targetObj = objIdMap.get(attrValue);
         if (targetObj != null)
         {
            creator.setValue(obj, attrName, targetObj, "new");
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
         int subTokenEnd = scanner.match().end() - 1;
         while ( ! subToken.endsWith("\"") && scanner.hasNext())
         {
            subToken = scanner.next();
            subTokenEnd = scanner.match().end() - 1;
         }
         
         lookAheadToken = yaml.substring(stringStartPos, subTokenEnd);
         if (lookAheadToken.startsWith("\"\""))
         {
            lookAheadToken = lookAheadToken.substring(2, lookAheadToken.length()-2);
         }
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

      SendableEntityCreator creator = getCreator(className);
      
      Object obj = creator.getSendableInstance(false);
      
      objIdMap.put(objectId, obj);
      
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
   
   Map<String, SendableEntityCreator> creatorMap = new SimpleKeyValueList<String, SendableEntityCreator>();
   
   public SendableEntityCreator getCreator(String clazzName) 
   {
      // already known? 
      SendableEntityCreator creator = creatorMap.get(clazzName);
      
      if (creator != null)
      {
         return creator;
      }
      
      // try reflection
      String creatorName = null;
         
      for (String packageName : packageNames)
      {
         String fullCreatorName = packageName + ".util." + clazzName + "Creator"; 
         
         try
         {
            Class<?> creatorClass = Class.forName(fullCreatorName);
            
            creator = (SendableEntityCreator) creatorClass.newInstance();
            
            if (creator != null)
            {
               creatorMap.put(clazzName, creator);
               return creator;
            }
         }
         catch (Exception e)
         {
            creator = null;
         }
      }
      
      // GenericCreator?
      for (String packageName : packageNames)
      {
         String fullClassName = packageName + "." + clazzName; 
         
         try
         {
            Class<?> theClass = Class.forName(fullClassName);
            
            if (theClass != null)
            {
               creator = new GenericCreator().withClassName(fullClassName);
               creatorMap.put(clazzName, creator);
               return creator;
            }
         }
         catch (Exception e)
         {
            creator = null;
         }
      }
      
      

      
      return creator;
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
         
         // already known? 
         String key = objIdMap.getKey(obj);
         
         if (key == null)
         {
            // add to map
            String className = obj.getClass().getName();
            
            className = CGUtil.shortClassName(className);
            
            key = className.substring(0, 1).toLowerCase();
            
            int num = objIdMap.size() + 1;
            
            key += num;
            
            objIdMap.put(key, obj);
            
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
         String className = CGUtil.shortClassName(obj.getClass().getName());
         
         
         buf.append("- ").append(key).append(": \t").append(className).append("\n");
         
         // attrs
         SendableEntityCreator creator = creatorMap.get(className);
         
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
                  buf.append("  ").append(prop).append(": \t").append(value).append("\n");
               }
            }
         }
         buf.append("\n");
      }
      
      return buf.toString();
   }

}
