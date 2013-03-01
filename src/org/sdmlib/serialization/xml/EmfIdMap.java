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
      
      addValues(rootFactory, xmlEntity, rootObject);
      
      addChildren(xmlEntity, rootFactory, rootObject);
      
      return rootObject;
   }

   private void addValues(EntityFactory rootFactory, XMLEntity xmlEntity,
         Object rootObject)
   {
      // add to map
      String id = (String) xmlEntity.get("xmi:id");
      
      id = "_" + id.substring(1);
      
      this.put(id, rootObject);
      
      // set plain attributes
      for (Iterator<String> iter = xmlEntity.keys(); iter.hasNext(); )
      {
         String key = iter.next();
         String value = (String) xmlEntity.get(key);
         
         if (value.startsWith("$"))
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
               
               System.out.println(kidObject.toString());
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
}
