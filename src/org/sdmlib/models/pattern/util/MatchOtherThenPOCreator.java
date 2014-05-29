package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.json.JsonIdMap;


public class MatchOtherThenPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MatchOtherThenPO();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
