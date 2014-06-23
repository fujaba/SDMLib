package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.ChangeHistory;

import de.uniks.networkparser.json.JsonIdMap;

public class ChangeHistoryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChangeHistoryPO(new ChangeHistory[]{});
      } else {
          return new ChangeHistoryPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
