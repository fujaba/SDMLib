package org.sdmlib.examples.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class NewMsgListener implements PropertyChangeListener
{
   /**
    * 
    */
   private final ReplicationChatClientGUI gui;

   /**
    * @param clientInitTask
    */
   NewMsgListener(ReplicationChatClientGUI gui)
   {
      this.gui = gui;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      ChatRoot root = (ChatRoot) evt.getSource();
      
      if (root.getMsgs().size() == 1)
      {
         gui.getChatContent().setText("");
      }
      
      ChatMsg newMsg = (ChatMsg) evt.getNewValue();
      
      if (newMsg == null)
      {
         // this was a remove. Just set full text a new
         gui.getChatContent().setText("");
         for (ChatMsg msg : root.getMsgs())
         {
            gui.getChatContent().append(msg + "\n");
         }
      }
      else
      {
         gui.getChatContent().append(newMsg + "\n");
      }
   }
}