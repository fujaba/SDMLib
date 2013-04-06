package org.sdmlib.examples.replication;

import org.sdmlib.examples.replication.creators.CreatorCreator;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReplicationChatServer extends ReplicationNode
{
   
   public static final int REPLICATION_SERVER_PORT = 11142;

   public static void main(String[] args)
   {
      ReplicationChatServer replicationServer = new ReplicationChatServer();
      
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
         
         
         ChatRoot chatRoot = new ChatRoot();

         map.put(spaceId + "_root", chatRoot);
      }
      
      return sharedSpace;
   }
}
