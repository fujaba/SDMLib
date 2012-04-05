package org.sdmlib.examples.studyright.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.Room;

public class RoomCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Room.PROPERTY_ROOMNO,
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_UNI,
      Room.PROPERTY_NEIGHBORS,
      Room.PROPERTY_STUDENTS,
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Room) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}



















































