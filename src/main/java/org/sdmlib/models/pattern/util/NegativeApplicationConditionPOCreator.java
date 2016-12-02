package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.NegativeApplicationCondition;

import de.uniks.networkparser.IdMap;

public class NegativeApplicationConditionPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new NegativeApplicationConditionPO(new NegativeApplicationCondition[]{});
      } else {
          return new NegativeApplicationConditionPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
