package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.historymanagement.marketmodel.Actor;

import de.uniks.networkparser.IdMap;

public class ActorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ActorPO(new Actor[]{});
      } else {
          return new ActorPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.historymanagement.marketmodel.util.CreatorCreator.createIdMap(sessionID);
   }
}
