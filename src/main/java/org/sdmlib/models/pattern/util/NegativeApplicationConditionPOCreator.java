package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.NegativeApplicationCondition;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
