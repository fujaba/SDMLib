package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.json.JsonIdMap;

public class NegativeApplicationConditionCreator extends PatternCreator
{
   private final String[] properties = new String[]
   {
      NegativeApplicationCondition.PROPERTY_HASMATCH,
      Pattern.PROPERTY_ELEMENTS, 
      Pattern.PROPERTY_CURRENTSUBPATTERN,
      NegativeApplicationCondition.PROPERTY_MODIFIER,
      NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      Pattern.PROPERTY_DEBUGMODE,
      PatternElement.PROPERTY_PATTERN,
      Pattern.PROPERTY_TRACE,
      Pattern.PROPERTY_RGRAPH,
      Pattern.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new NegativeApplicationCondition();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((NegativeApplicationCondition) entity).removeYou();
   }
}
