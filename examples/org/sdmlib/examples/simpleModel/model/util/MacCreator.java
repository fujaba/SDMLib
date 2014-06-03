package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.simpleModel.model.Mac;

public class MacCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Mac.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Mac();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Mac.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Mac) target).getName();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Mac.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Mac) target).setName((String) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Mac) entity).removeYou();
   }
}
