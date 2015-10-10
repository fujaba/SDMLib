package org.sdmlib.replication;

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class ReplicationObjectScenarioForCoverage
{
   @Test
   public void testReplicationObjectScenarioForCoverage()
   {
      Storyboard story = new Storyboard();
      
      story.add("Create some objects just for coverage. This does not serve as an usage example.");
      
      ReplicationNode replicationNode = new ReplicationNode().withSpaceId("coverage");
      
      SharedSpace sharedSpace = replicationNode.createSharedSpaces();
      
      sharedSpace.createChannels();
      
      ReplicationServer replicationServer = new ReplicationServer();
      
      ServerSocketAcceptThread serverSocketAcceptThread = new ServerSocketAcceptThread();
      
      RemoteTaskBoard remoteTaskBoard = new RemoteTaskBoard();
      
      Lane lane = remoteTaskBoard.createLanes().withName("todo");
      
      BoardTask task1 = lane.createTask("firstTask");
      BoardTask task2 = lane.createTask("secondTask");
      task1.withNext(task2);
      
      
      LogEntry logEntry = task1.createLogEntries();
      
      ReplicationRoot replicationRoot = new ReplicationRoot();
      
      ChangeHistory changeHistory = new ChangeHistory();
      
      changeHistory.createChanges();
      
      story.addObjectDiagram(replicationNode, replicationServer, serverSocketAcceptThread, remoteTaskBoard, 
         replicationRoot, changeHistory);
      
      story.dumpHTML();
   }
}
