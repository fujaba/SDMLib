package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.examples.studyrightWithAssignments.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightWithAssignments.Room;

public class RoomCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Room.PROPERTY_NAME,
      Room.PROPERTY_TOPIC,
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_UNIVERSITY,
      Room.PROPERTY_DOORS,
      Room.PROPERTY_STUDENTS,
      Room.PROPERTY_ASSIGNMENTS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Room();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Room) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Room) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Room) entity).removeYou();
   }
}

