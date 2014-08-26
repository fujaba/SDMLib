package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public abstract class RemoteTaskListener implements PropertyChangeListener
{
   public static final String NEW = "NEW";
   protected SharedSpace sharedSpace;
   private HashMap<String, Runnable> tasks;
   private boolean isInit;

   public RemoteTaskListener()
   {
      tasks = new HashMap<String, Runnable>();
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (!isInit)
      {
         if (isInit = init(evt))
         {
            wrapEvent(evt);
         }
         else
         {
            throw new RuntimeException("Initialization failure!");
         }
      }
      else
      {
         wrapEvent(evt);
      }
   }

   private void wrapEvent(PropertyChangeEvent evt)
   {
      boolean oldIsApplyingChangeFlag = getSharedSpace().isApplyingChangeMsg();
      getSharedSpace().setApplyingChangeMsg(false);
      try
      {
         handleEvent(evt);
      }
      finally
      {
         getSharedSpace().setApplyingChangeMsg(oldIsApplyingChangeFlag);
      }
   }

   protected void handleTasks(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null)
      {
         BoardTask task = (BoardTask) evt.getNewValue();
         String name = task.getName();
         runTask(name);
      }
   }
   
   public RemoteTaskListener withTask(String name, Runnable task) {
      tasks.put(name, task);
      return this;
   }
   
   public void runTask(String name) {
      Runnable task = tasks.get(name);
      if(task != null) {
         task.run();
      } else {
         throw new RuntimeException("No such task: " + name);
      }
   }
   
   public SharedSpace getSharedSpace()
   {
      return sharedSpace;
   }
   
   protected abstract boolean init(PropertyChangeEvent evt);

   protected abstract void handleEvent(PropertyChangeEvent evt);

}
