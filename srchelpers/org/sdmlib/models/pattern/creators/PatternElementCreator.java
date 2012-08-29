package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PatternElementCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_PATTERN,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PatternElement) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PatternElement) entity).removeYou();
   }
}















