package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.TaskHandler;

public class PlayerLaneListener implements PropertyChangeListener
{
   
   LinkedHashSet<TaskHandler> handlerlist = new LinkedHashSet<TaskHandler>();
   
   public PlayerLaneListener init(MauMauClientGui gui)
   {
      handlerlist.add(new ShowStartGameButton().withGui(gui));
      
      return this;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals(Lane.PROPERTY_TASKS))
      {
         BoardTask oldTask = (BoardTask) evt.getOldValue();
         BoardTask newTask = (BoardTask) evt.getNewValue();
         // some task change
         for (TaskHandler handler : handlerlist)
         {
            boolean done = handler.handle(oldTask, newTask);
            
            if (done)
            {
               break;
            }
         }
      }
   }
}
