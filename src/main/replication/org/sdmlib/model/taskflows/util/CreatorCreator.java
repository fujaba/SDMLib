package org.sdmlib.model.taskflows.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.TaskFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.TaskFlowPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.PeerProxyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SocketThreadCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SocketThreadPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.FetchFileFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.FetchFileFlowPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.LoggerCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.LoggerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.LogEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.LogEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.TimerCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.TimerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SDMTimerCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SDMTimerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.taskflows.util.PeerProxyPOCreator());

      return jsonIdMap;
   }
}
