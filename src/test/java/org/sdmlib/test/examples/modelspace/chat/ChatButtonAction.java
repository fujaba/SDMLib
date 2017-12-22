package org.sdmlib.test.examples.modelspace.chat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChatButtonAction implements EventHandler<ActionEvent>
{

   private MSChatClient app;

   public ChatButtonAction(MSChatClient chatClientApp)
   {
      this.app = chatClientApp;
      // TODO AZ: this is really outdated, use lambdas instead. 
   }

   @Override
   public void handle(ActionEvent arg0)
   {
      // get text from inputField
      String text = app.getChatInput().getText();
      
      app.getChatInput().setText("");
      
      // create ChatMessage in current channel
      MSChatChannel currentChannel = app.getChannel();
      
      MSChatMsg msg = new MSChatMsg().withText(text).withSender(app.getUserName()).withTime(System.currentTimeMillis());
      
      currentChannel.withMsgs(msg);
      
//      System.out.println("new message: " + text);
   }

}
