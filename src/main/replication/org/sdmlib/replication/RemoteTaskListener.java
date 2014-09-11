package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public abstract class RemoteTaskListener implements PropertyChangeListener
{
   public static final String NEW = "NEW";
   protected SharedSpace sharedSpace;
   private HashMap<String, RemoteTask> tasks;
   private boolean isInit;

   public RemoteTaskListener()
   {
      tasks = new HashMap<String, RemoteTask>();
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (!isInit)
      {
         if (isInit = init(evt))
         {
            handleEvent(evt);
         }
         else
         {
            throw new RuntimeException("Initialization failure!");
         }
      }
      else
      {
         handleEvent(evt);
      }
   }

   private void handleEvent(PropertyChangeEvent evt)
   {
      boolean oldIsApplyingChangeFlag = getSharedSpace().isApplyingChangeMsg();
      sharedSpace.setApplyingChangeMsg(false);
      try
      {
         String propertyName = evt.getPropertyName();
         switch (propertyName)
         {
         case ReplicationRoot.PROPERTY_APPLICATIONOBJECT:
            handleReplicationChange(evt);
            break;
         case RemoteTaskListener.NEW:
            createLane();
            break;
         case Lane.PROPERTY_TASKS:
            handleTasks(evt);
            break;
         default:
         }
      }
      finally
      {
         sharedSpace.setApplyingChangeMsg(oldIsApplyingChangeFlag);
      }
   }

   protected void handleTasks(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null)
      {
         BoardTask task = (BoardTask) evt.getNewValue();
         runTask(task);
      }
   }
   
   public RemoteTaskListener withTask(String name, RemoteTask task) {
      tasks.put(name, task);
      return this;
   }
   
   public void runTask(BoardTask boardTask) {
      RemoteTask task = tasks.get(boardTask.getName());
      if(task != null) {
         task.withBoardTask(boardTask);
         task.run();
      } else {
         throw new RuntimeException("No such task: " + boardTask.getName());
      }
   }
   
   public SharedSpace getSharedSpace()
   {
      return sharedSpace;
   }
   
   protected abstract void createLane();
   
   protected abstract boolean init(PropertyChangeEvent evt);

   protected abstract void handleReplicationChange(PropertyChangeEvent evt);

}
