package org.sdmlib.replication;

import org.sdmlib.replication.SharedSpace.ChannelMsg;

public class JavaFXMsgHandler implements Runnable
{
   private ChannelMsg channelMsg;
   private SharedSpace space;

   public JavaFXMsgHandler(SharedSpace space, ChannelMsg channelMsg)
   {
      this.channelMsg = channelMsg;
      this.space = space;
   }

   @Override
   public void run()
   {
      space.handleMessage(channelMsg);
   }
}