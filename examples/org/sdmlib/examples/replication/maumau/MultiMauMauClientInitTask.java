package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import org.sdmlib.examples.replication.ChatRoot;
import org.sdmlib.examples.replication.SWTSharedSpace;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;


public class MultiMauMauClientInitTask implements Runnable
{

   private MauMauClientGui gui;
   private String[] args;
   private MultiMauMauControler gameControler;
   private String nodeId;
   private MauMau mauMau;
   private PlayerLaneListener laneListener;
   private SharedSpace gameSpace;

   public MultiMauMauClientInitTask(MauMauClientGui gui, String[] args)
   {
      this.gui = gui;
      this.args = args;
   }

   @Override
   public void run()
   {
      nodeId = args[0];

      gameSpace = new SWTSharedSpace()
      .withSpaceId("game42")
      .withNodeId(nodeId);

      gameSpace.setName("GameSpace" + nodeId);

      ReplicationChannel channel = gameSpace.createChannels()
            .withConnect("localhost", ReplicationMauMauServer.REPLICATION_SERVER_PORT);
      channel.setName("ReplicationChannel" + nodeId + "Server");
      channel.start();

      // connect to shared space
      JsonObject jsonObject = new JsonObject();
      jsonObject.put(SharedSpace.PROPERTY_SPACEID, gameSpace.getSpaceId());

      String line = jsonObject.toString();
      channel.send(line);

      // set up history
      ChangeHistory history = new ChangeHistory();
      gameSpace.setHistory(history);

      // create initial chat model
      JsonIdMap map = org.sdmlib.examples.replication.maumau.creators.CreatorCreator.createIdMap(nodeId);
      map.withCreator(org.sdmlib.replication.creators.CreatorCreator.getCreatorSet());
      gameSpace.withMap(map);

      mauMau = new MauMau();

      map.put(gameSpace.getSpaceId() + "_root", mauMau);

      gameControler = new MultiMauMauControler(mauMau, gui, gameSpace).init();
      mauMau.getPropertyChangeSupport().addPropertyChangeListener(gameControler);

      // create task board and start action
      TaskFlowBoard taskFlowBoard = new TaskFlowBoard();
      map.put(gameSpace.getSpaceId() + "taskBoard", taskFlowBoard);

      taskFlowBoard.getPropertyChangeSupport().addPropertyChangeListener(new TaskFlowBoardListener());
   }

   private final class TaskFlowBoardListener implements
   PropertyChangeListener
   {
      @Override
      public void propertyChange(PropertyChangeEvent evt)
      {
         Lane anyPlayerLane = null;

         if (TaskFlowBoard.PROPERTY_LANES.equals(evt.getPropertyName()))
         {
            anyPlayerLane = (Lane) evt.getNewValue();


            if (anyPlayerLane != null && MultiMauMau.ANY_PLAYER.equals(anyPlayerLane.getName()))
            {
               boolean oldStatus = gameSpace.isApplyingChangeMsg();
               try
               {
                  gameSpace.setApplyingChangeMsg(false);
                  // add listener
                  PlayerLaneListener laneListener = new PlayerLaneListener().init(gui);
                  anyPlayerLane.getPropertyChangeSupport()
                     .addPropertyChangeListener(laneListener);
                  // add myself
                  Player me = new Player();
                  me.withName(nodeId);
                  System.out.println("Player name: " + me.getName());
                  gameControler.setActivePlayer(me);
                  mauMau.withPlayers(me);
                  TaskFlowBoard taskFlowBoard = anyPlayerLane.getBoard();
                  Lane myLane = new Lane().withName(nodeId + "Lane");
                  taskFlowBoard.addToLanes(myLane);
                  myLane.getPropertyChangeSupport().addPropertyChangeListener(
                     laneListener);
               }
               finally 
               {
                  gameSpace.setApplyingChangeMsg(oldStatus);
               }
            }

         }
      }
   }


}
