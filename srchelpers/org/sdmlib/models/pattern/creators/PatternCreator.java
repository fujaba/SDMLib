package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.Pattern;

public class PatternCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Pattern.PROPERTY_ELEMENTS,
      Pattern.PROPERTY_HASMATCH,
      Pattern.PROPERTY_CURRENTNAC,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Pattern();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Pattern) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Pattern) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}




