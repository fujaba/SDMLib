package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.ReachableState;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

