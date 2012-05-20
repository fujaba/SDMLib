package org.sdmlib.examples.studyright.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.University;

public class UniversityCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_STUDENTS,
      University.PROPERTY_ROOMS,
   };
   
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((University) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

