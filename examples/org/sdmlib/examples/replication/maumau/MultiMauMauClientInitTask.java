package org.sdmlib.examples.replication.maumau;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import org.sdmlib.examples.replication.ChatRoot;
import org.sdmlib.examples.replication.SWTSharedSpace;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;
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
            .withConnect("localhost", ReplicationServer.REPLICATION_SERVER_PORT);
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
      map.addCreator(org.sdmlib.replication.creators.CreatorCreator.getCreatorSet());
      gameSpace.withMap(map);
      
      MauMau mauMau = new MauMau();
      
      mauMau = gameSpace.glueObjectsAtId(gameSpace.getSpaceId() + "_root", mauMau);
      
      
      MauMauControler gameControler = new MauMauControler(mauMau, gui);
      mauMau.getPropertyChangeSupport().addPropertyChangeListener(gameControler);
      
      Player me = new Player();
      me = gameSpace.glueObjectsAtId(nodeId, me);
      
      me.withName(nodeId);
      mauMau.withPlayers(me);
      
      // tom.withNext(albert);
      // albert.withNext(tom);
      
      gameControler.setActivePlayer(me);
      
      // mauMau.setCurrentMove(albert);
      
      // deal cards
      Player[] players = mauMau.getPlayers().toArray(new Player[] {});
      
      int i = 0;
      int p = 0;

      CardSet cards = new CardSet();
      cards.addAll(mauMau.getCards());
      
      Collections.shuffle(cards);
      
      for (Card c : cards)
      {
         if (p < players.length)
         {
            players[p].addToCards(c);
            
            i++;
            
            if ( ! (i < 5))
            {
               i = 0;
               p++;
            }
         }
         else if (p == players.length)
         {
            // put one card on the stack
            mauMau.getStack().addToCards(c);
            p++;
         }
         else
         { 
            // all other cards put on the deck
            mauMau.getDeck().addToCards(c);
         }
      }
      
   }

}
