package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.transformations.Match;

import de.uniks.networkparser.json.JsonIdMap;

public class MatchPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MatchPO(new Match[]{});
      } else {
          return new MatchPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }
}
