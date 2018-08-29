package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import de.uniks.networkparser.IdMap;


public class StudentCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      Student.PROPERTY_ASSIGNMENTPOINTS,
      Student.PROPERTY_CREDITS,
      Student.PROPERTY_FRIENDS,
      Student.PROPERTY_ID,
      Student.PROPERTY_MOTIVATION,
      Student.PROPERTY_NAME,
      Student.PROPERTY_UNIVERSITY,
      Student.PROPERTY_IN,
      Student.PROPERTY_DONE,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new Student();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if(attribute == null || entity instanceof Student == false) {
          return null;
      }
      Student element = (Student)entity;
      if (Student.PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attribute))
      {
         return element.getAssignmentPoints();
      }

      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         return element.getCredits();
      }

      if (Student.PROPERTY_FRIENDS.equalsIgnoreCase(attribute))
      {
         return element.getFriends();
      }

      if (Student.PROPERTY_ID.equalsIgnoreCase(attribute))
      {
         return element.getId();
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attribute))
      {
         return element.getMotivation();
      }

      if (Student.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return element.getName();
      }

      if (Student.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         return element.getUniversity();
      }

      if (Student.PROPERTY_IN.equalsIgnoreCase(attribute))
      {
         return element.getIn();
      }

      if (Student.PROPERTY_DONE.equalsIgnoreCase(attribute))
      {
         return element.getDone();
      }

      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value, String type)
   {
      if(attribute == null || entity instanceof Student == false) {
          return false;
      }
      Student element = (Student)entity;
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attribute = attribute + type;
      }

      if (Student.PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attribute))
      {
         element.setAssignmentPoints((int) value);
         return true;
      }

      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         element.setCredits((int) value);
         return true;
      }

      if (Student.PROPERTY_FRIENDS.equalsIgnoreCase(attribute))
      {
         element.withFriends((Student) value);
         return true;
      }

      if (Student.PROPERTY_ID.equalsIgnoreCase(attribute))
      {
         element.setId((String) value);
         return true;
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attribute))
      {
         element.setMotivation((int) value);
         return true;
      }

      if (Student.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         element.setName((String) value);
         return true;
      }

      if (Student.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         element.setUniversity((University) value);
         return true;
      }

      if (Student.PROPERTY_IN.equalsIgnoreCase(attribute))
      {
         element.setIn((Room) value);
         return true;
      }

      if (Student.PROPERTY_DONE.equalsIgnoreCase(attribute))
      {
         element.withDone((Assignment) value);
         return true;
      }

      return false;
   }

   public static IdMap createIdMap(String session) {
      return CreatorCreator.createIdMap(session);
   }
}