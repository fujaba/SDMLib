package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.model.test.consistence.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.test.consistence.Field;

public class FieldCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Field.PROPERTY_BORDER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Field();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Field) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Field) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Field) entity).removeYou();
   }
}

