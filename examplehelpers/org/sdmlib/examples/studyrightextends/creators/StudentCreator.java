package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightextends.Student;

public class StudentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Student.PROPERTY_NAME, Student.PROPERTY_MATRNO, Student.PROPERTY_LECTURE, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Student();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((Student) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Student) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((Student) entity).removeYou();
   }
}
