package org.sdmlib.examples.replication.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.examples.replication.chat.util.ChatChannelSet;

public class ChatTextUpdater implements PropertyChangeListener
{

   private ReplicationChatClientApp app;
   private ChatChannelSet knownChannels = new ChatChannelSet();
   
   public ChatTextUpdater(ReplicationChatClientApp replicationChatClientApp)
   {
      this.app = replicationChatClientApp;
   }

   public void setCurrentChannel(ChatChannel currentChannel)
   {
      if ( ! knownChannels.contains(currentChannel))
      {
         knownChannels.add(currentChannel);
         currentChannel.getPropertyChangeSupport().addPropertyChangeListener(ChatChannel.PROPERTY_MSGS, this);
      }
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getSource() instanceof ChatChannel && evt.getNewValue() != null)
      {
         ChatChannel channel = (ChatChannel) evt.getSource();
         
         if (channel == app.getCurrentChannel())
         {
            ChatMsg newChatMsg = (ChatMsg) evt.getNewValue();
            
            newChatMsg.getPropertyChangeSupport().addPropertyChangeListener(this);
         }
      }
      
      String newText = app.getCurrentChannel().getMsgs().getText().concat("\n");
      
      app.getChatText().setText(newText);
   }

}
