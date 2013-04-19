package org.sdmlib.examples.replication.maumau;

import org.sdmlib.examples.replication.creators.CreatorCreator;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.SharedSpace;
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
   public org.sdmlib.replication.SharedSpace getOrCreateSharedSpace(String spaceId) 
   {
      SharedSpace sharedSpace = super.getOrCreateSharedSpace(spaceId);

      if (sharedSpace.getMap() == null)
      {
         JsonIdMap map = CreatorCreator.createIdMap("server");
         
         sharedSpace.withNodeId("server").withMap(map);
         
         // create game
         MauMau mauMau = new MauMau();

         map.put(spaceId + "_root", mauMau);

         // create cards
         mauMau.createDeck();
         mauMau.createStack();
               
         int suitCount = 0;
         for (Suit s : Suit.values())
         {
            for (Value v : Value.values())
            {
               Card card = new Card().withSuit(s).withValue(v);
               mauMau.addToCards(card);
            }
            suitCount++;
            if (suitCount >= 2)
            {
               break;
            }
         }
      }
      
      return sharedSpace;
   }
}
