package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.OptionalSubPattern;

import de.uniks.networkparser.json.JsonIdMap;

public class OptionalSubPatternPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new OptionalSubPatternPO(new OptionalSubPattern[]{});
      } else {
         return new OptionalSubPatternPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

