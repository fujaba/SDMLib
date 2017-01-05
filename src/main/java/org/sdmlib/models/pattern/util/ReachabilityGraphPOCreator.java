package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.ReachabilityGraph;

import de.uniks.networkparser.IdMap;

public class ReachabilityGraphPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ReachabilityGraphPO(new ReachabilityGraph[]{});
      } else {
          return new ReachabilityGraphPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
