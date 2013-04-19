package org.sdmlib.examples.replication;


import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

public class ClientInitTask implements Runnable
{

   public static final String NINA_ALBERT_CHAT = "nina_albert_chat";
   private String[] args;
   private ReplicationChatClientGUI gui;

   public ClientInitTask(ReplicationChatClientGUI window, String[] args)
   {
      gui = window;
      this.args = args;
   }

   @Override
   public void run()
   {
      String nodeId = args[0];
      
      // create shared space
      SharedSpace chatSpace = new SWTSharedSpace()
      .withSpaceId(NINA_ALBERT_CHAT)
      .withNodeId(nodeId);
      
      chatSpace.setName("ChatSpace" + nodeId);
            
      ReplicationChannel channel = chatSpace.createChannels()
            .withConnect("localhost", ReplicationServer.REPLICATION_SERVER_PORT);
      channel.setName("ReplicationChannel" + nodeId + "Server");
      channel.start();
      
      // connect to shared space
      JsonObject jsonObject = new JsonObject();
      jsonObject.put(SharedSpace.PROPERTY_SPACEID, NINA_ALBERT_CHAT);
      
      String line = jsonObject.toString();
      channel.send(line);
      
      // set up history
      ChangeHistory history = new ChangeHistory();
      chatSpace.setHistory(history);
      
      // init user interface
      gui.getLblYourName().setText(nodeId);
      gui.getLblServerip().setText("localhost");
      
      // create initial chat model
      JsonIdMap map = org.sdmlib.examples.replication.creators.CreatorCreator.createIdMap(nodeId);
      chatSpace.withMap(map);
      
      ChatRoot chatRoot = new ChatRoot();
      
      chatRoot.getPropertyChangeSupport().addPropertyChangeListener(ChatRoot.PROPERTY_MSGS, new NewMsgListener(gui));

      gui.setRoot(chatRoot);
      
      // map.executeUpdateMsg(element)
      map.put(NINA_ALBERT_CHAT + "_root", chatRoot);
      
      chatSpace.loadHistoryFromFile();
      
      // chatSpace.resync();
      
      if (chatRoot.getMsgs().size() == 0)
      {
         ChatMsg msg = new ChatMsg().withText("Hello")
               .withSender(chatSpace.getNodeId())
               .withRoot(chatRoot);
      }
      else
      {
         ChatMsg msg = new ChatMsg().withText("Hello again")
               .withSender(chatSpace.getNodeId())
               .withRoot(chatRoot);
      }
      
      // update gui
      System.out.println("Connection established");
   }

}
