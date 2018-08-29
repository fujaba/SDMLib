package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import de.uniks.networkparser.IdMap;


public class UniversityCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_STUDENTS,
      University.PROPERTY_ROOMS,
      University.PROPERTY_PRESIDENT,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new University();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if(attribute == null || entity instanceof University == false) {
          return null;
      }
      University element = (University)entity;
      if (University.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return element.getName();
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return element.getStudents();
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attribute))
      {
         return element.getRooms();
      }

      if (University.PROPERTY_PRESIDENT.equalsIgnoreCase(attribute))
      {
         return element.getPresident();
      }

      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value, String type)
   {
      if(attribute == null || entity instanceof University == false) {
          return false;
      }
      University element = (University)entity;
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attribute = attribute + type;
      }

      if (University.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         element.setName((String) value);
         return true;
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         element.withStudents((Student) value);
         return true;
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attribute))
      {
         element.withRooms((Room) value);
         return true;
      }

      if (University.PROPERTY_PRESIDENT.equalsIgnoreCase(attribute))
      {
         element.setPresident((President) value);
         return true;
      }

      return false;
   }

   public static IdMap createIdMap(String session) {
      return CreatorCreator.createIdMap(session);
   }
}