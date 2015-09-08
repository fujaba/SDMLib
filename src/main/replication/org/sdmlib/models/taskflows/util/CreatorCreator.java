package org.sdmlib.models.taskflows.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.TaskFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.TaskFlowPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.PeerProxyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SocketThreadCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SocketThreadPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.FetchFileFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.FetchFileFlowPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.LoggerCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.LoggerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.LogEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.LogEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.TimerCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.TimerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SDMTimerCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SDMTimerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.taskflows.util.PeerProxyPOCreator());

      jsonIdMap.withCreator(new TaskFlowCreator());
      jsonIdMap.withCreator(new TaskFlowPOCreator());
      jsonIdMap.withCreator(new PeerProxyCreator());
      jsonIdMap.withCreator(new PeerProxyPOCreator());
      jsonIdMap.withCreator(new SocketThreadCreator());
      jsonIdMap.withCreator(new SocketThreadPOCreator());
      jsonIdMap.withCreator(new FetchFileFlowCreator());
      jsonIdMap.withCreator(new FetchFileFlowPOCreator());
      jsonIdMap.withCreator(new LoggerCreator());
      jsonIdMap.withCreator(new LoggerPOCreator());
      jsonIdMap.withCreator(new LogEntryCreator());
      jsonIdMap.withCreator(new LogEntryPOCreator());
      jsonIdMap.withCreator(new TimerCreator());
      jsonIdMap.withCreator(new TimerPOCreator());
      jsonIdMap.withCreator(new SDMTimerCreator());
      jsonIdMap.withCreator(new SDMTimerPOCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new ObjectCreator());
      jsonIdMap.withCreator(new ObjectPOCreator());
      jsonIdMap.withCreator(new TaskFlowCreator());
      jsonIdMap.withCreator(new TaskFlowPOCreator());
      jsonIdMap.withCreator(new PeerProxyCreator());
      jsonIdMap.withCreator(new PeerProxyPOCreator());
      jsonIdMap.withCreator(new SocketThreadCreator());
      jsonIdMap.withCreator(new SocketThreadPOCreator());
      jsonIdMap.withCreator(new FetchFileFlowCreator());
      jsonIdMap.withCreator(new FetchFileFlowPOCreator());
      jsonIdMap.withCreator(new LoggerCreator());
      jsonIdMap.withCreator(new LoggerPOCreator());
      jsonIdMap.withCreator(new LogEntryCreator());
      jsonIdMap.withCreator(new LogEntryPOCreator());
      jsonIdMap.withCreator(new TimerCreator());
      jsonIdMap.withCreator(new TimerPOCreator());
      jsonIdMap.withCreator(new SDMTimerCreator());
      jsonIdMap.withCreator(new SDMTimerPOCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new ObjectCreator());
      jsonIdMap.withCreator(new ObjectPOCreator());
      jsonIdMap.withCreator(new TaskFlowCreator());
      jsonIdMap.withCreator(new TaskFlowPOCreator());
      jsonIdMap.withCreator(new PeerProxyCreator());
      jsonIdMap.withCreator(new PeerProxyPOCreator());
      jsonIdMap.withCreator(new SocketThreadCreator());
      jsonIdMap.withCreator(new SocketThreadPOCreator());
      jsonIdMap.withCreator(new FetchFileFlowCreator());
      jsonIdMap.withCreator(new FetchFileFlowPOCreator());
      jsonIdMap.withCreator(new LoggerCreator());
      jsonIdMap.withCreator(new LoggerPOCreator());
      jsonIdMap.withCreator(new LogEntryCreator());
      jsonIdMap.withCreator(new LogEntryPOCreator());
      jsonIdMap.withCreator(new TimerCreator());
      jsonIdMap.withCreator(new TimerPOCreator());
      jsonIdMap.withCreator(new SDMTimerCreator());
      jsonIdMap.withCreator(new SDMTimerPOCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new ObjectCreator());
      jsonIdMap.withCreator(new ObjectPOCreator());
      return jsonIdMap;
   }
}
