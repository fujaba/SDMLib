package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Lecture;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Professor;
import org.sdmlib.examples.studyright.model.Student;

public class LectureCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Lecture.PROPERTY_TITLE,
      Lecture.PROPERTY_IN,
      Lecture.PROPERTY_HAS,
      Lecture.PROPERTY_LISTEN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Lecture();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Lecture.PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         return ((Lecture) target).getTitle();
      }

      if (Lecture.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return ((Lecture) target).getIn();
      }

      if (Lecture.PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         return ((Lecture) target).getHas();
      }

      if (Lecture.PROPERTY_LISTEN.equalsIgnoreCase(attrName))
      {
         return ((Lecture) target).getListen();
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

      if (Lecture.PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setTitle((String) value);
         return true;
      }

      if (Lecture.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setIn((Room) value);
         return true;
      }

      if (Lecture.PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setHas((Professor) value);
         return true;
      }

      if (Lecture.PROPERTY_LISTEN.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setListen((Student) value);
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
      ((Lecture) entity).removeYou();
   }
}

