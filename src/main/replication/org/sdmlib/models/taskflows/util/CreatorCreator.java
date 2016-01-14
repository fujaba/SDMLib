package org.sdmlib.models.taskflows.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.TaskFlowCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.TaskFlowPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.PeerProxyCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.PeerProxyPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SocketThreadCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SocketThreadPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.FetchFileFlowCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.FetchFileFlowPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.LoggerCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.LoggerPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.LogEntryCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.LogEntryPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.TimerCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.TimerPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SDMTimerCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SDMTimerPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SDMLibJsonIdMapCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.PeerProxyCreator());
      jsonIdMap.with(new org.sdmlib.models.taskflows.util.PeerProxyPOCreator());

      jsonIdMap.with(new TaskFlowCreator());
      jsonIdMap.with(new TaskFlowPOCreator());
      jsonIdMap.with(new PeerProxyCreator());
      jsonIdMap.with(new PeerProxyPOCreator());
      jsonIdMap.with(new SocketThreadCreator());
      jsonIdMap.with(new SocketThreadPOCreator());
      jsonIdMap.with(new FetchFileFlowCreator());
      jsonIdMap.with(new FetchFileFlowPOCreator());
      jsonIdMap.with(new LoggerCreator());
      jsonIdMap.with(new LoggerPOCreator());
      jsonIdMap.with(new LogEntryCreator());
      jsonIdMap.with(new LogEntryPOCreator());
      jsonIdMap.with(new TimerCreator());
      jsonIdMap.with(new TimerPOCreator());
      jsonIdMap.with(new SDMTimerCreator());
      jsonIdMap.with(new SDMTimerPOCreator());
      jsonIdMap.with(new SDMLibJsonIdMapCreator());
      jsonIdMap.with(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TaskFlowCreator());
      jsonIdMap.with(new TaskFlowPOCreator());
      jsonIdMap.with(new PeerProxyCreator());
      jsonIdMap.with(new PeerProxyPOCreator());
      jsonIdMap.with(new SocketThreadCreator());
      jsonIdMap.with(new SocketThreadPOCreator());
      jsonIdMap.with(new FetchFileFlowCreator());
      jsonIdMap.with(new FetchFileFlowPOCreator());
      jsonIdMap.with(new LoggerCreator());
      jsonIdMap.with(new LoggerPOCreator());
      jsonIdMap.with(new LogEntryCreator());
      jsonIdMap.with(new LogEntryPOCreator());
      jsonIdMap.with(new TimerCreator());
      jsonIdMap.with(new TimerPOCreator());
      jsonIdMap.with(new SDMTimerCreator());
      jsonIdMap.with(new SDMTimerPOCreator());
      jsonIdMap.with(new SDMLibJsonIdMapCreator());
      jsonIdMap.with(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TaskFlowCreator());
      jsonIdMap.with(new TaskFlowPOCreator());
      jsonIdMap.with(new PeerProxyCreator());
      jsonIdMap.with(new PeerProxyPOCreator());
      jsonIdMap.with(new SocketThreadCreator());
      jsonIdMap.with(new SocketThreadPOCreator());
      jsonIdMap.with(new FetchFileFlowCreator());
      jsonIdMap.with(new FetchFileFlowPOCreator());
      jsonIdMap.with(new LoggerCreator());
      jsonIdMap.with(new LoggerPOCreator());
      jsonIdMap.with(new LogEntryCreator());
      jsonIdMap.with(new LogEntryPOCreator());
      jsonIdMap.with(new TimerCreator());
      jsonIdMap.with(new TimerPOCreator());
      jsonIdMap.with(new SDMTimerCreator());
      jsonIdMap.with(new SDMTimerPOCreator());
      jsonIdMap.with(new SDMLibJsonIdMapCreator());
      jsonIdMap.with(new SDMLibJsonIdMapPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      return jsonIdMap;
   }
}
