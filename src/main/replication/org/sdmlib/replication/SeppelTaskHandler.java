package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uniks.networkparser.list.SimpleKeyValueList;

public class SeppelTaskHandler implements PropertyChangeListener
{

   private SeppelSpace seppelSpace;

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null)
      {
         BoardTask task = (BoardTask) evt.getNewValue();
         
         SeppelBoardTaskAction runnable = handlerList.get(task.getName());
         
         boolean oldApplyingChangeMsg = seppelSpace.isApplyingChangeMsg();
         try
         {
            seppelSpace.setApplyingChangeMsg(false);
            
            runnable.run(task);
         }
         catch (Exception e)
         {
            // exception during exception handling? => abort
            // else call exception handling
            if ( ! Exception.class.getName().equals(task.getName()))
            {
               BoardTask exceptionTask = new BoardTask().withName(Exception.class.getName());
               exceptionTask.withTaskObject("failedTask", task).withTaskObject(Exception.class.getName(), e);
               PropertyChangeEvent newEvt = new PropertyChangeEvent(evt.getSource(), evt.getPropertyName(), evt.getOldValue(), exceptionTask);
               this.propertyChange(newEvt);
            }   
            else
            {
               e.printStackTrace(); 
            }
         }
         finally 
         {
            seppelSpace.setApplyingChangeMsg(oldApplyingChangeMsg);
         }
         
      }
      
   }

   public SeppelTaskHandler withSeppelSpace(SeppelSpace seppelSpace)
   {
      this.seppelSpace = seppelSpace;
      
      return this;
   }

   SimpleKeyValueList<String, SeppelBoardTaskAction> handlerList = new SimpleKeyValueList<String, SeppelBoardTaskAction>();
   
   public SeppelTaskHandler with(String string, SeppelBoardTaskAction runnable)
   {
      handlerList.put(string, runnable);
      
      return this;
   }

}
