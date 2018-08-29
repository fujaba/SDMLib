package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import de.uniks.networkparser.IdMap;


public class TeachingAssistantCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      TeachingAssistant.PROPERTY_CERTIFIED,
      TeachingAssistant.PROPERTY_ROOM,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new TeachingAssistant();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if(attribute == null || entity instanceof TeachingAssistant == false) {
          return null;
      }
      TeachingAssistant element = (TeachingAssistant)entity;
      if (TeachingAssistant.PROPERTY_CERTIFIED.equalsIgnoreCase(attribute))
      {
         return element.isCertified();
      }

      if (TeachingAssistant.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         return element.getRoom();
      }

      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value, String type)
   {
      if(attribute == null || entity instanceof TeachingAssistant == false) {
          return false;
      }
      TeachingAssistant element = (TeachingAssistant)entity;
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attribute = attribute + type;
      }

      if (TeachingAssistant.PROPERTY_CERTIFIED.equalsIgnoreCase(attribute))
      {
         element.setCertified((boolean) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         element.setRoom((Room) value);
         return true;
      }

      return false;
   }

   public static IdMap createIdMap(String session) {
      return CreatorCreator.createIdMap(session);
   }
}