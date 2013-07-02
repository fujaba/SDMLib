package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.TaskHandler;

class ServerLaneListener implements PropertyChangeListener
{
   private SharedSpace sharedSpace;
   
   private LinkedHashSet<TaskHandler> handlerlist = new LinkedHashSet<TaskHandler>();
   
   public ServerLaneListener init()
   {
      handlerlist.add(new ServerStartGameHandler(sharedSpace));
      
      return this;
   }

   

   public ServerLaneListener(SharedSpace sharedSpace)
   {
      this.sharedSpace = sharedSpace;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals(Lane.PROPERTY_TASKS) && evt.getNewValue() != null)
      {
         boolean oldState = sharedSpace.isApplyingChangeMsg();
         
         try
         {
            sharedSpace.setApplyingChangeMsg(false);
            
            BoardTask oldTask = (BoardTask) evt.getOldValue();
            BoardTask newTask = (BoardTask) evt.getNewValue();
            
            for (TaskHandler handler : handlerlist)
            {
               boolean done = handler.handle(oldTask, newTask);
               
               if (done)
               {
                  break;
               }
            }
         }
         finally
         {
            sharedSpace.setApplyingChangeMsg(oldState);
         }
      }
   }
}