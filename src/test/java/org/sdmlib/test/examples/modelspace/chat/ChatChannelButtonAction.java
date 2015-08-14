package org.sdmlib.test.examples.modelspace.chat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class ChatChannelButtonAction implements EventHandler<ActionEvent>
{

   private MSChatGroupClient msChatGroupClient;

   public ChatChannelButtonAction(MSChatGroupClient msChatGroupClient)
   {
      this.msChatGroupClient = msChatGroupClient;
      // TODO Auto-generated constructor stub
   }

   @Override
   public void handle(ActionEvent arg0)
   {
      // get data and construct channel description
      MSChatChannelDescription channelDesc = new MSChatChannelDescription();
      
      TextField chatChannelInput = msChatGroupClient.getChatChannelInput();
      
      String text = chatChannelInput.getText();
      
      if (text != null)
      {
         String[] split = text.split(" ");
         
         if (split.length > 0)
         {
            channelDesc.setName(split[0]);
         }
         
         if (split.length > 1)
         {
            channelDesc.setLocation(split[1] + "/" + split[0]);
         }
         else
         {
            channelDesc.setLocation("chatchannels" + "/" + split[0]);
         }
         
         msChatGroupClient.getGroup().withChannels(channelDesc);
         
         chatChannelInput.setText("");
      }
      
      
   }

}
