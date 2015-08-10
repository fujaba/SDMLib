package org.sdmlib.test.examples.modelspace.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class ChatTextUpdater implements PropertyChangeListener
{
   /**
    * 
    */
   private final MSChatClient gui;
   private MSChatChannel channel;

   /**
    * @param msChatClient
    * @param channel 
    */
   ChatTextUpdater(MSChatClient msChatClient, MSChatChannel channel)
   {
      gui = msChatClient;
      this.channel = channel;
      
      for (MSChatMsg msg : channel.getMsgs())
      {
         msg.addPropertyChangeListener(this);
      }
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Object source = evt.getSource();
      
      Object newValue = evt.getNewValue();
      
      if (source instanceof MSChatChannel && newValue != null && evt.getPropertyName().equals(MSChatChannel.PROPERTY_MSGS))
      {
         MSChatMsg newMsg = (MSChatMsg) newValue;
         
         newMsg.addPropertyChangeListener(this);
      }
      
      String text = channel.getMsgs().toString("\n");
      
      gui.getChatText().setText(text);
      
   }
}