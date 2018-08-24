package org.sdmlib.test.codegen.studyright.model.util;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.codegen.studyright.model.University;
import org.sdmlib.test.codegen.studyright.model.Student;
import de.uniks.networkparser.IdMap;


public class UniversityCreator implements SendableEntityCreator
{

   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_STUDENTS,
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
      int pos = attribute.indexOf('.');
      String attrName = attribute;

      if (pos > 0)
      {
         attrName = attribute.substring(0, pos);
      }
      if(attrName.length()<1) {
         return null;
      }

      if (University.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return element.getName();
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return element.getStudents();
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

      return false;
   }
    public IdMap createMap(String session) {
 	   return CreatorCreator.createIdMap(session);
    }
}