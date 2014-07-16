package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchIsomorphicConstraint;

import de.uniks.networkparser.json.JsonIdMap;

public class MatchIsomorphicConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new MatchIsomorphicConstraintPO(new MatchIsomorphicConstraint[]{});
      } else {
         return new MatchIsomorphicConstraintPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

