package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class OptionalSubPatternCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      OptionalSubPattern.PROPERTY_MODIFIER,
      OptionalSubPattern.PROPERTY_HASMATCH,
      OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME,
      OptionalSubPattern.PROPERTY_DOALLMATCHES,
      OptionalSubPattern.PROPERTY_MATCHFORWARD,
      OptionalSubPattern.PROPERTY_CURRENTSUBPATTERN,
      Pattern.PROPERTY_DEBUGMODE,
      Pattern.PROPERTY_ELEMENTS,
      PatternElement.PROPERTY_PATTERN,
      Pattern.PROPERTY_TRACE,
      Pattern.PROPERTY_RGRAPH,
      Pattern.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new OptionalSubPattern();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((OptionalSubPattern) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((OptionalSubPattern) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((OptionalSubPattern) entity).removeYou();
   }
}






