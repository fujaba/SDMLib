package org.sdmlib.examples.studyright.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.studyright.Assignment;

public class AssignmentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_NAME,
      Assignment.PROPERTY_POINTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Assignment();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getName();
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getPoints();
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

      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setName((String) value);
         return true;
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setPoints(Integer.parseInt(value.toString()));
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
      ((Assignment) entity).removeYou();
   }
}

