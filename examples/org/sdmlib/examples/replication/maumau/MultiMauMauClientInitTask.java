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

   public MultiMauMauClientInitTask(MauMauClientGui gui, String[] args)
   {
      this.gui = gui;
      this.args = args;
   }

   @Override
   public void run()
   {
      String nodeId = args[0];
      
      // create shared space
      SharedSpace gameSpace = new SWTSharedSpace()
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
      
      MauMau mauMau = new MauMau();
      
      mauMau = gameSpace.glueObjectsAtId(gameSpace.getSpaceId() + "_root", mauMau);
      
      
      MauMauControler gameControler = new MauMauControler(mauMau, gui);
      mauMau.getPropertyChangeSupport().addPropertyChangeListener(gameControler);
      
      // create task board and start action
      TaskFlowBoard taskFlowBoard = new TaskFlowBoard();
      map.put(gameSpace.getSpaceId() + "taskBoard", taskFlowBoard);
      
      Lane anyPlayerLane = new Lane().withName("anyPlayer");
      map.put("anyPlayerLane", taskFlowBoard);
      taskFlowBoard.addToLanes(anyPlayerLane);
      
      anyPlayerLane.getPropertyChangeSupport().addPropertyChangeListener(new LaneListener());
      
      Player me = new Player();
      me.withName(nodeId);
      mauMau.withPlayers(me);
      
   }

}
