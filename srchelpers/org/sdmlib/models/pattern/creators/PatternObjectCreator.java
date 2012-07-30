package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

public class PatternObjectCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      PatternObject.PROPERTY_CURRENTMATCH,
      PatternObject.PROPERTY_INCOMMING,
      PatternObject.PROPERTY_OUTGOING,
      PatternObject.PROPERTY_CANDIDATES,
      PatternObject.PROPERTY_ATTRCONSTRAINTS,
      PatternElement.PROPERTY_PATTERN, 
      PatternElement.PROPERTY_MODIFIER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObject();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternObject) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternObject) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}





