package org.sdmlib.simple.model.issue30.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.issue30.ZoombieOwner;

public class ZoombieOwnerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ZoombieOwnerPO(new ZoombieOwner[]{});
      } else {
          return new ZoombieOwnerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.issue30.util.CreatorCreator.createIdMap(sessionID);
   }
}
