package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.ReplicationRoot;

import de.uniks.networkparser.json.JsonIdMap;

public class ReplicationRootPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ReplicationRootPO(new ReplicationRoot[]{});
      } else {
          return new ReplicationRootPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
