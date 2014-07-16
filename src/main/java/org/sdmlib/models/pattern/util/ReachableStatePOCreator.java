package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.ReachableState;

import de.uniks.networkparser.json.JsonIdMap;

public class ReachableStatePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ReachableStatePO(new ReachableState[]{});
      } else {
          return new ReachableStatePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

