package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Female;

public class FemaleCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Female.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Female();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Female.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Female) target).getName();
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

      if (Female.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Female) target).setName((String) value);
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
      ((Female) entity).removeYou();
   }
}

