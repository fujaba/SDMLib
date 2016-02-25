package org.sdmlib.replication.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new ThreadCreator());
      jsonIdMap.with(new ThreadPOCreator());
      jsonIdMap.with(new SocketCreator());
      jsonIdMap.with(new SocketPOCreator());
      jsonIdMap.with(new ReplicationNodeCreator());
      jsonIdMap.with(new ReplicationNodePOCreator());
      jsonIdMap.with(new SharedSpaceCreator());
      jsonIdMap.with(new SharedSpacePOCreator());
      jsonIdMap.with(new ReplicationChannelCreator());
      jsonIdMap.with(new ReplicationChannelPOCreator());
      jsonIdMap.with(new ReplicationServerCreator());
      jsonIdMap.with(new ReplicationServerPOCreator());
      jsonIdMap.with(new ServerSocketAcceptThreadCreator());
      jsonIdMap.with(new ServerSocketAcceptThreadPOCreator());
      jsonIdMap.with(new TaskCreator());
      jsonIdMap.with(new TaskPOCreator());
      jsonIdMap.with(new LogEntryCreator());
      jsonIdMap.with(new LogEntryPOCreator());
      jsonIdMap.with(new ChangeHistoryCreator());
      jsonIdMap.with(new ChangeHistoryPOCreator());
      jsonIdMap.with(new ReplicationChangeCreator());
      jsonIdMap.with(new ReplicationChangePOCreator());
      jsonIdMap.with(new RemoteTaskBoardCreator());
      jsonIdMap.with(new RemoteTaskBoardPOCreator());
      jsonIdMap.with(new LaneCreator());
      jsonIdMap.with(new LanePOCreator());
      jsonIdMap.with(new BoardTaskCreator());
      jsonIdMap.with(new BoardTaskPOCreator());
      jsonIdMap.with(new RemoteTaskCreator());
      jsonIdMap.with(new RemoteTaskPOCreator());
      jsonIdMap.with(new ReplicationRootCreator());
      jsonIdMap.with(new ReplicationRootPOCreator());
      jsonIdMap.with(new PropertyChangeEventCreator());
      jsonIdMap.with(new PropertyChangeEventPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new ChangeEventCreator());
      jsonIdMap.with(new ChangeEventPOCreator());
      jsonIdMap.with(new ChangeEventListCreator());
      jsonIdMap.with(new ChangeEventListPOCreator());
      jsonIdMap.with(new RunnableCreator());
      jsonIdMap.with(new RunnablePOCreator());
      jsonIdMap.with(new SeppelSpaceCreator());
      jsonIdMap.with(new SeppelSpacePOCreator());
      jsonIdMap.with(new SeppelSpaceProxyCreator());
      jsonIdMap.with(new SeppelSpaceProxyPOCreator());
      jsonIdMap.with(new SeppelScopeCreator());
      jsonIdMap.with(new SeppelScopePOCreator());
      jsonIdMap.with(new SeppelChannelCreator());
      jsonIdMap.with(new SeppelChannelPOCreator());
      return jsonIdMap;
   }
}
