package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.ChangeEvent;

import de.uniks.networkparser.IdMap;

public class ChangeEventPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChangeEventPO(new ChangeEvent[]{});
      } else {
          return new ChangeEventPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
}
