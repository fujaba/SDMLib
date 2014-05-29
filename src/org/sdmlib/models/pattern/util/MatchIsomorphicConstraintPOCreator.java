package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.json.JsonIdMap;

public class MatchIsomorphicConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MatchIsomorphicConstraintPO();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

