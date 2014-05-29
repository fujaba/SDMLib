package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.json.JsonIdMap;

public class CloneOpCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new CloneOp();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CloneOp) entity).removeYou();
   }
}
