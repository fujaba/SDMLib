package org.sdmlib.test.examples.replication.chat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChatButtonAction implements EventHandler<ActionEvent>
{

   private ReplicationChatClientApp app;

   public ChatButtonAction(ReplicationChatClientApp replicationChatClientApp)
   {
      this.app = replicationChatClientApp;
      // TODO Auto-generated constructor stub
   }

   @Override
   public void handle(ActionEvent arg0)
   {
      // get text from inputField
      String text = app.getChatInput().getText();
      
      app.getChatInput().setText("");
      
      // create ChatMessage in current channel
      ChatChannel currentChannel = app.getCurrentChannel();
      
      ChatMsg msg = new ChatMsg().withText(text).withSender(app.getSelfUser().getUserName());
      
      msg.withChannel(currentChannel);
      
      System.out.println("new message: " + text);
   }

}
