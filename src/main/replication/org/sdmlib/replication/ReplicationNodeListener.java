package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class ReplicationNodeListener implements PropertyChangeListener
{

   protected SharedSpace sharedSpace;
   private boolean isInit;

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
      boolean oldIsApplyingChangeFlag = sharedSpace.isApplyingChangeMsg();
      sharedSpace.setApplyingChangeMsg(false);
      try
      {
         handleEvent(evt);
      }
      finally
      {
         sharedSpace.setApplyingChangeMsg(oldIsApplyingChangeFlag);
      }
   }

   protected abstract boolean init(PropertyChangeEvent evt);

   protected abstract void handleEvent(PropertyChangeEvent evt);

}
