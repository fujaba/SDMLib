package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightextends.Lecture;

public class LectureCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Lecture.PROPERTY_TITLE,
      Lecture.PROPERTY_IN,
      Lecture.PROPERTY_HAS,
      Lecture.PROPERTY_LISTEN,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Lecture();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Lecture) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Lecture) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

