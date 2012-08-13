package org.sdmlib.models.classes.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.ClassModel;

public class ClassModelCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      ClassModel.PROPERTY_CLASSES,
      ClassModel.PROPERTY_ASSOCIATIONS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModel();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClassModel) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ClassModel) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ClassModel) entity).removeYou();
   }
}



