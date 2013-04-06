package org.sdmlib.examples.replication;

import org.eclipse.swt.widgets.Display;
import org.sdmlib.replication.SharedSpace;

public class SWTSharedSpace extends SharedSpace
{
   @Override
   public void enqueueMsg(String msg)
   {
      // create a handling task and enqueue at display
      Display.getDefault().asyncExec(new HandleMsgTask(msg));
      
   }

   private class HandleMsgTask implements Runnable
   {
      private String msg;

      public HandleMsgTask(String msg)
      {
         this.msg = msg;
      }

      @Override
      public void run()
      {
         handleMessage(msg);
      }
   }

}
