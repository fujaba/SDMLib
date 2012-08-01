package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.PatternElement;

public class PatternElementCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_PATTERN,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PatternElement();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternElement) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternElement) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}







