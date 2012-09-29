package org.sdmlib.models.classes.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.Clazz;

public class ClazzCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Clazz.PROPERTY_NAME,
      Clazz.PROPERTY_INTERFAZE,
      Clazz.PROPERTY_CLASSMODEL,
      Clazz.PROPERTY_KINDCLASSES,
      Clazz.PROPERTY_SUPERCLASS,
      Clazz.PROPERTY_KINDCLASSESASINTERFACE,
      Clazz.PROPERTY_INTERFACES,
      Clazz.PROPERTY_ATTRIBUTES,
      Clazz.PROPERTY_METHODS,
      Clazz.PROPERTY_SOURCEROLES,
      Clazz.PROPERTY_TARGETROLES,
      Clazz.PROPERTY_EXTERNAL,
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




