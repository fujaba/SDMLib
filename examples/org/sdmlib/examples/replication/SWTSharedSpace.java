package org.sdmlib.examples.replication;

import org.eclipse.swt.widgets.Display;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.SharedSpace;

public class SWTSharedSpace extends SharedSpace
{
   @Override
   public void enqueueMsg(ReplicationChannel channel, String msg)
   {
      // create a handling task and enqueue at display
      ChannelMsg channelMsg = new ChannelMsg(channel, msg);
      Display.getDefault().asyncExec(new HandleMsgTask(channelMsg));
      
   }

   private class HandleMsgTask implements Runnable
   {
      private ChannelMsg msg;

      public HandleMsgTask(ChannelMsg msg)
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
