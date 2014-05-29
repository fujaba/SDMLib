package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.json.JsonIdMap;

public class OptionalSubPatternPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new OptionalSubPatternPO();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

