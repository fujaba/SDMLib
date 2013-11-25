package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.Clazz;

public class ClazzCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Clazz.PROPERTY_NAME,
      Clazz.PROPERTY_INTERFAZE,
      Clazz.PROPERTY_EXTERNAL,
      Clazz.PROPERTY_WRAPPED,
      Clazz.PROPERTY_FILEPATH,
      Clazz.PROPERTY_CLASSMODEL,
      Clazz.PROPERTY_KIDCLASSES,
      Clazz.PROPERTY_SUPERCLASS,
      Clazz.PROPERTY_KIDCLASSESASINTERFACE,
      Clazz.PROPERTY_INTERFACES,
      Clazz.PROPERTY_ATTRIBUTES,
      Clazz.PROPERTY_METHODS,
      Clazz.PROPERTY_SOURCEROLES,
      Clazz.PROPERTY_TARGETROLES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Clazz();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Clazz) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Clazz) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Clazz) entity).removeYou();
   }
}


