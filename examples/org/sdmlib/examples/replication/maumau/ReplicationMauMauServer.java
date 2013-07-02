package org.sdmlib.examples.replication.maumau;


import org.eclipse.swt.widgets.TaskBar;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.Step;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReplicationMauMauServer extends ReplicationNode
{
   
   public static final int REPLICATION_SERVER_PORT = 11144;

   public static void main(String[] args)
   {
      ReplicationMauMauServer replicationServer = new ReplicationMauMauServer();
      
      new ServerSocketAcceptThread(replicationServer, REPLICATION_SERVER_PORT).start();
   }
   
   @Override
   public synchronized org.sdmlib.replication.SharedSpace getOrCreateSharedSpace(String spaceId) 
   {
      SharedSpace sharedSpace = super.getOrCreateSharedSpace(spaceId);

      if (sharedSpace.getMap() == null)
      {
         JsonIdMap map = org.sdmlib.examples.replication.maumau.creators.CreatorCreator.createIdMap(MultiMauMau.SERVER);
         map.withCreator(org.sdmlib.replication.creators.CreatorCreator.getCreatorSet());
         
         sharedSpace.withNodeId(MultiMauMau.SERVER).withMap(map);
         
         // create task board and start action
         TaskFlowBoard taskFlowBoard = new TaskFlowBoard();
         map.put(spaceId + "taskBoard", taskFlowBoard);
         
         Lane serverLane = taskFlowBoard.createLanes().withName(MultiMauMau.SERVER);
         
         serverLane.getPropertyChangeSupport().addPropertyChangeListener(new ServerLaneListener(sharedSpace).init());
         
         Lane anyPlayerLane = new Lane().withName("anyPlayer");
         // map.put("anyPlayerLane", anyPlayerLane);
         
         taskFlowBoard.addToLanes(anyPlayerLane);
         
         
         Step startGameStep = taskFlowBoard.createSteps().withName("Start Game");
         
         startGameStep.createTasks().withName(MultiMauMau.START_GAME)
         .withLane(anyPlayerLane);
         
         // start game is handled by:
         Class c = ShowStartGameButton.class;
         
         // create game
         MauMau mauMau = new MauMau();

         map.put(spaceId + "_root", mauMau);
      }
      
      return sharedSpace;
   }
}
