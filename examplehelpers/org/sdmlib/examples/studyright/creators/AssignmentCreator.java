package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.Assignment;

public class AssignmentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_NAME,
      Assignment.PROPERTY_POINTS,
      Assignment.PROPERTY_ROOM,
      Assignment.PROPERTY_STUDENTS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Assignment();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Assignment) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Assignment) target).set(attrName, value);
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

