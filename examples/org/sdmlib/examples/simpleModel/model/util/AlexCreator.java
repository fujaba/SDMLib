package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.simpleModel.model.Alex;

public class AlexCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Alex.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Alex();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Alex.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Alex) target).getName();
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

      if (Alex.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Alex) target).setName((String) value);
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
      ((Alex) entity).removeYou();
   }
}
