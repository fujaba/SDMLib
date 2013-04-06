package org.sdmlib.examples.replication;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

class NewMsgAction extends KeyAdapter implements SelectionListener
{
   /**
    * 
    */
   private final ReplicationChatClientGUI gui;

   /**
    * @param replicationChatClientGUI
    */
   NewMsgAction(ReplicationChatClientGUI replicationChatClientGUI)
   {
      gui = replicationChatClientGUI;
   }

   public void run()
   {
      String text = gui.getNewMsg().getText();
      gui.getNewMsg().setText("");
      
      ChatMsg newMsg = new ChatMsg()
      .withText(text)
      .withSender(gui.getLblYourName().getText());
      
      gui.getRoot().withMsgs(newMsg);
   }
   
   @Override
   public void keyReleased(KeyEvent e) 
   {
      if (e.keyCode == 13)
      {
         run();
      }
   }

   @Override
   public void widgetSelected(SelectionEvent e)
   {
      run();
   }

   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
      run();
   }
}