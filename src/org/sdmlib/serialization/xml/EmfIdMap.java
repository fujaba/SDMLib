package org.sdmlib.serialization.xml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.utils.StrUtil;


public class EmfIdMap extends SDMLibJsonIdMap
{
   private static final String XMI_ID = "xmi:id";

   public Object decode(StringBuilder fileText)
   {
      int pos = fileText.indexOf("\n");
      fileText.delete(0, pos);

      String string = fileText.toString();

      return decode(string);
   }

   public Object decode(String emfxml)
   {
      EntityFactory rootFactory = null;
      XMLEntity xmlEntity = new XMLEntity(emfxml);
      
      // build root entity
      String tag = xmlEntity.getTag();
      
      String className = tag.split("\\:")[1];
      
      for (String fullName : creators.keySet())
      {
         if (CGUtil.shortClassName(fullName).equals(className))
         {
            rootFactory = (EntityFactory) getCreatorClasses(fullName);
            break;
         }
      }
      
      Object rootObject = rootFactory.getSendableInstance(false);
      
      addXMIIds(xmlEntity);
      
      addValues(rootFactory, xmlEntity, rootObject);
      
      addChildren(xmlEntity, rootFactory, rootObject);
      
      return rootObject;
   }

   private void addXMIIds(XMLEntity xmlEntity)
   {
      if (xmlEntity.has(XMI_ID))
      {
         return;
      }
      
      xmlEntity.put(XMI_ID, "$n1");
      int i = 0;
      String firstTag="p";
      for (XMLEntity kid : xmlEntity.getChildren())
      {
         if( ! kid.getTag().startsWith(firstTag))
         {
            i = 0;
            firstTag = "t";
         }
         kid.put(XMI_ID, "$"  + firstTag + i); 
         i++;
      }
   }

   private void addValues(EntityFactory rootFactory, XMLEntity xmlEntity,
         Object rootObject)
   {
      // add to map
      String id = (String) xmlEntity.get(XMI_ID);
      
      id = "_" + id.substring(1);
      
      this.put(id, rootObject);
      
      // set plain attributes
      for (Iterator<String> iter = xmlEntity.keys(); iter.hasNext(); )
      {
         String key = iter.next();
         String value = ((String) xmlEntity.get(key)).trim();
         
         if (value == null || "".equals(value))
         {
            continue;
         }
         else if (value.startsWith("$"))
         {
            for (String ref : value.split(" "))
            {
               String myRef = "_" + ref.substring(1);
               if (getObject(myRef) != null)
               {
                  rootFactory.setValue(rootObject, key, getObject(myRef), "");
               }
            }
         }
         else if (value.startsWith("//@"))
         {
            for (String ref : value.split(" "))
            {
               String myRef = ref.substring(3);
               myRef = "_" + myRef.subSequence(0, 1) + myRef.split("\\.")[1];
               if (getObject(myRef) != null)
               {
                  rootFactory.setValue(rootObject, key, getObject(myRef), "");
               }
            }
         }
         else
         {
            rootFactory.setValue(rootObject, key, value, "");
         }
      }
   }

   private void addChildren(XMLEntity xmlEntity, EntityFactory rootFactory,
         Object rootObject)
   {
      for (XMLEntity kidEntity : xmlEntity.getChildren())
      {
         String tag = kidEntity.getTag();
         
         // identify kid type
         try
         {
            Method getMethod = rootObject.getClass().getMethod("get" + StrUtil.upFirstChar(tag));
            String typeName = getMethod.getReturnType().getName();
            
            typeName = CGUtil.baseClassName(typeName, "Set");
            if (typeName != null)
            {
               EntityFactory kidFactory = (EntityFactory) getCreatorClasses(typeName);
               
               Object kidObject = kidFactory.create();
               
               addValues(kidFactory, kidEntity, kidObject);
               
               rootFactory.setValue(rootObject, tag, kidObject, "");
               
               addChildren(kidEntity, kidFactory, kidObject);
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }         
      }
      
   }

   public EmfIdMap withCreators(LinkedHashSet<SendableEntityCreator> creatorSet)
   {
      addCreator(creatorSet);
      return this;
   }

   public Object decodeFile(String fileName)
   {
      StringBuilder fileText = CGUtil.readFile(fileName);
      
      return decode(fileText);
   }
}
