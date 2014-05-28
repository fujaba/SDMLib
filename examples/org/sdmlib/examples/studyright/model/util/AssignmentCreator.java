package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Assignment;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Student;

public class AssignmentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_NAME,
      Assignment.PROPERTY_POINTS,
      Assignment.PROPERTY_ASSIGNMENTS,
      Assignment.PROPERTY_STUDENTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Assignment();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getName();
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getPoints();
      }

      if (Assignment.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getAssignments();
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return ((Assignment) target).getStudents();
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

      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setName((String) value);
         return true;
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (Assignment.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).addToAssignments((Room) value);
         return true;
      }
      
      if ((Assignment.PROPERTY_ASSIGNMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Assignment) target).removeFromAssignments((Room) value);
         return true;
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setStudents((Student) value);
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
      ((Assignment) entity).removeYou();
   }
}

