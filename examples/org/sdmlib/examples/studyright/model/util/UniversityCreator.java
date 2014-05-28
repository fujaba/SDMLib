package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.University;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Student;

public class UniversityCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_ROOMS,
      University.PROPERTY_STUDENTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new University();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (University.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((University) target).getName();
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attrName))
      {
         return ((University) target).getRooms();
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return ((University) target).getStudents();
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

      if (University.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((University) target).setName((String) value);
         return true;
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attrName))
      {
         ((University) target).addToRooms((Room) value);
         return true;
      }
      
      if ((University.PROPERTY_ROOMS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).removeFromRooms((Room) value);
         return true;
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((University) target).addToStudents((Student) value);
         return true;
      }
      
      if ((University.PROPERTY_STUDENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).removeFromStudents((Student) value);
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
      ((University) entity).removeYou();
   }
}


