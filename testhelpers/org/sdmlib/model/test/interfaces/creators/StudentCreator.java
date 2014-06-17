package org.sdmlib.model.test.interfaces.creators;

import org.sdmlib.model.classes.creators.CreatorCreator;
import org.sdmlib.model.test.interfaces.Student;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class StudentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {};

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
