package org.sdmlib.replication.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ThreadCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ThreadPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SocketCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SocketPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationNodeCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationNodePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SharedSpaceCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SharedSpacePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationChannelCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationChannelPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationServerCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationServerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ServerSocketAcceptThreadCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ServerSocketAcceptThreadPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.TaskCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.TaskPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.LogEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.LogEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ChangeHistoryCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ChangeHistoryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationChangeCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationChangePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.RemoteTaskBoardCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.RemoteTaskBoardPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.LaneCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.LanePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.BoardTaskCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.BoardTaskPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationRootCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationRootPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationNodeCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ReplicationNodePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.RemoteTaskCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.RemoteTaskPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelSpaceCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelSpacePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelSpaceProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelSpaceProxyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelChannelCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelChannelPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelScopeCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.SeppelScopePOCreator());
      jsonIdMap.withCreator(new PropertyChangeEventWrapper());
      
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ChangeEventCreator());
      jsonIdMap.withCreator(new org.sdmlib.replication.util.ChangeEventPOCreator());
      return jsonIdMap;
   }
}
