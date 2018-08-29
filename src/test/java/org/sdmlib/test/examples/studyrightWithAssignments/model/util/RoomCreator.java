package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import de.uniks.networkparser.IdMap;


public class RoomCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_DOORS,
      Room.PROPERTY_STUDENTS,
      Room.PROPERTY_NAME,
      Room.PROPERTY_ASSIGNMENTS,
      Room.PROPERTY_TAS,
      Room.PROPERTY_UNIVERSITY,
      Room.PROPERTY_TOPIC,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new Room();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if(attribute == null || entity instanceof Room == false) {
          return null;
      }
      Room element = (Room)entity;
      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         return element.getCredits();
      }

      if (Room.PROPERTY_DOORS.equalsIgnoreCase(attribute))
      {
         return element.getDoors();
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return element.getStudents();
      }

      if (Room.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return element.getName();
      }

      if (Room.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attribute))
      {
         return element.getAssignments();
      }

      if (Room.PROPERTY_TAS.equalsIgnoreCase(attribute))
      {
         return element.getTas();
      }

      if (Room.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         return element.getUniversity();
      }

      if (Room.PROPERTY_TOPIC.equalsIgnoreCase(attribute))
      {
         return element.getTopic();
      }

      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value, String type)
   {
      if(attribute == null || entity instanceof Room == false) {
          return false;
      }
      Room element = (Room)entity;
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attribute = attribute + type;
      }

      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         element.setCredits((int) value);
         return true;
      }

      if (Room.PROPERTY_DOORS.equalsIgnoreCase(attribute))
      {
         element.withDoors((Room) value);
         return true;
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         element.withStudents((Student) value);
         return true;
      }

      if (Room.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         element.setName((String) value);
         return true;
      }

      if (Room.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attribute))
      {
         element.withAssignments((Assignment) value);
         return true;
      }

      if (Room.PROPERTY_TAS.equalsIgnoreCase(attribute))
      {
         element.withTas((TeachingAssistant) value);
         return true;
      }

      if (Room.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         element.setUniversity((University) value);
         return true;
      }

      if (Room.PROPERTY_TOPIC.equalsIgnoreCase(attribute))
      {
         element.setTopic((String) value);
         return true;
      }

      return false;
   }

   public static IdMap createIdMap(String session) {
      return CreatorCreator.createIdMap(session);
   }
}