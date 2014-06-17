package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.University;

public class UniversityCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { University.PROPERTY_NAME, University.PROPERTY_STUDENTS,
         University.PROPERTY_ROOMS, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new University();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((University) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((University) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((University) entity).removeYou();
   }
}
