package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class RoomCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Room.PROPERTY_ROOMNO,
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_LECTURE,
      Room.PROPERTY_UNI,
      Room.PROPERTY_NEIGHBORS,
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



