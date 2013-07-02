package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.TaskHandler;

public class ShowStartGameButton extends TaskHandler
{

   private MauMauClientGui gui = null;
   
   public ShowStartGameButton withGui(MauMauClientGui gui)
   {
      this.gui = gui;
      return this;
   }

   private final class StartGameAction extends MouseAdapter
   {
      private BoardTask t;

      public StartGameAction(BoardTask t)
      {
         this.t = t;
      }

      @Override
      public void mouseUp(MouseEvent e) 
      {
         // move start game task to server lane
         TaskFlowBoard board = t.getLane().getBoard();
         
         Lane serverLane = board.getLanes(MultiMauMau.SERVER);
         
         serverLane.addToTasks(t);
         
         // this should trigger ??? at server side
      }
   }

   private Label startGameLabel;

   @Override
   public boolean handle(BoardTask oldTask, BoardTask newTask)
   {
      if (newTask != null && MultiMauMau.START_GAME.equals(newTask.getName()))
      {  
         startGameLabel = new Label(gui .getShell(), SWT.NONE);
         startGameLabel.setBounds(200, 200, 292, 50);
         startGameLabel.setAlignment(SWT.CENTER);
         startGameLabel.setText("When all players have shown up, Start Game");
         
         startGameLabel.addMouseListener(new StartGameAction(newTask));
         return true;
      }
      else if (oldTask != null && MultiMauMau.START_GAME.equals(oldTask.getName()))
      {
         // hide start game label
         startGameLabel.dispose();
      }
      
      
      return false;
   }
   
}
