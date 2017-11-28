package org.sdmlib.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import org.sdmlib.serialization.NullCreator;
import org.sdmlib.storyboards.GenericCreator;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.AbstractArray;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;
import sun.security.util.Length;

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
      
      while ( ! "".equals(currentToken))
      {
         System.out.println(">"+currentToken+"<");
         nextToken();
      }
      
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
      // TODO Auto-generated method stub
      
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
         while ( ! currentToken.equals("") && ! currentToken.endsWith(":") )
         {
            String attrValue = currentToken;
            
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
            
            nextToken();
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
      }
      else
      {
         lookAheadToken = "";
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
   
   private Map<String, Object> objIdMap = new SimpleKeyValueList<String, Object>().withFlag(AbstractArray.BIDI);

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

   private Object parseYaml()
   {
      Object root = null;
      
      while (scanner.hasNext("-"))
      {
         String token = scanner.next("-");
         String key = scanner.next();
         String second = scanner.next();
         
         if (second.endsWith(":"))
         {
            root = parseObjList(key, second);
         }
         else
         {
            root = parsePlainObject(key, second);
         }
         
      }
      return root;
   }

   private Object parsePlainObject(String key, String className)
   {
      
      // plainObject ::= - objId': ' type \n attr*
      // attr ::= attrName': ' attrValue \n 
      
      String id = stripColon(key);
      
      // get creator
      // create object
      // store in id map
      
      // read attributes
      
      while ( ! scanner.hasNext("-") && scanner.hasNext())
      {
         String attrName = scanner.next();
         parseValues(id, attrName);
         
         // put attr value
      }
      
      return null;
   }



   private ArrayList<String> parseValues(String id, String attrName)
   {
      return null;
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

}
