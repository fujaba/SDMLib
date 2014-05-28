package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Professor;
import org.sdmlib.examples.studyright.model.Female;
import org.sdmlib.examples.studyright.model.Lecture;

public class ProfessorCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Professor.PROPERTY_PERSNR,
      Female.PROPERTY_NAME,
      Professor.PROPERTY_LECTURE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Professor();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Professor.PROPERTY_PERSNR.equalsIgnoreCase(attrName))
      {
         return ((Professor) target).getPersNr();
      }

      if (Female.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Female) target).getName();
      }

      if (Professor.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         return ((Professor) target).getLecture();
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

      if (Professor.PROPERTY_PERSNR.equalsIgnoreCase(attrName))
      {
         ((Professor) target).setPersNr(Integer.parseInt(value.toString()));
         return true;
      }

      if (Female.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Female) target).setName((String) value);
         return true;
      }

      if (Professor.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         ((Professor) target).addToLecture((Lecture) value);
         return true;
      }
      
      if ((Professor.PROPERTY_LECTURE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Professor) target).removeFromLecture((Lecture) value);
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
      ((Professor) entity).removeYou();
   }
}

