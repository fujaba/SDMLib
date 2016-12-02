package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.CardinalityConstraint;

import de.uniks.networkparser.IdMap;

public class CardinalityConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CardinalityConstraintPO(new CardinalityConstraint[]{});
      } else {
          return new CardinalityConstraintPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
