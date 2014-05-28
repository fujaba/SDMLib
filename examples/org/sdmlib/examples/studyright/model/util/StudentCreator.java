package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Student;
import org.sdmlib.examples.studyright.model.Lecture;
import org.sdmlib.examples.studyright.model.University;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Assignment;

public class StudentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Student.PROPERTY_NAME,
      Student.PROPERTY_MATRNO,
      Student.PROPERTY_LECTURE,
      Student.PROPERTY_CREDITS,
      Student.PROPERTY_MOTIVATION,
      Student.PROPERTY_UNI,
      Student.PROPERTY_IN,
      Student.PROPERTY_DONE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Student();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Student.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getName();
      }

      if (Student.PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getMatrNo();
      }

      if (Student.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getLecture();
      }

      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getCredits();
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getMotivation();
      }

      if (Student.PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getUni();
      }

      if (Student.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getIn();
      }

      if (Student.PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         return ((Student) target).getDone();
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

      if (Student.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Student) target).setName((String) value);
         return true;
      }

      if (Student.PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         ((Student) target).setMatrNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         ((Student) target).addToLecture((Lecture) value);
         return true;
      }
      
      if ((Student.PROPERTY_LECTURE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Student) target).removeFromLecture((Lecture) value);
         return true;
      }

      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         ((Student) target).setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         ((Student) target).setMotivation(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         ((Student) target).setUni((University) value);
         return true;
      }

      if (Student.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         ((Student) target).setIn((Room) value);
         return true;
      }

      if (Student.PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         ((Student) target).addToDone((Assignment) value);
         return true;
      }
      
      if ((Student.PROPERTY_DONE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Student) target).removeFromDone((Assignment) value);
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
      ((Student) entity).removeYou();
   }
}


