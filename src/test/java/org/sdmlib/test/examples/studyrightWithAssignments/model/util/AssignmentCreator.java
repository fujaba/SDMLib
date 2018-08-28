package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import de.uniks.networkparser.IdMap;


public class AssignmentCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_ROOM,
      Assignment.PROPERTY_CONTENT,
      Assignment.PROPERTY_STUDENTS,
      Assignment.PROPERTY_POINTS,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new Assignment();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if(attribute == null || entity instanceof Assignment == false) {
          return null;
      }
      Assignment element = (Assignment)entity;
      if (Assignment.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         return element.getRoom();
      }

      if (Assignment.PROPERTY_CONTENT.equalsIgnoreCase(attribute))
      {
         return element.getContent();
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return element.getStudents();
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attribute))
      {
         return element.getPoints();
      }

      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value, String type)
   {
      if(attribute == null || entity instanceof Assignment == false) {
          return false;
      }
      Assignment element = (Assignment)entity;
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attribute = attribute + type;
      }

      if (Assignment.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         element.setRoom((Room) value);
         return true;
      }

      if (Assignment.PROPERTY_CONTENT.equalsIgnoreCase(attribute))
      {
         element.setContent((String) value);
         return true;
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         element.withStudents((Student) value);
         return true;
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attribute))
      {
         element.setPoints((int) value);
         return true;
      }

      return false;
   }
    public IdMap createMap(String session) {
 	   return CreatorCreator.createIdMap(session);
    }
}