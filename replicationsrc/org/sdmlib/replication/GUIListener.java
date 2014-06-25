package org.sdmlib.replication;

import org.sdmlib.replication.SharedSpace.ChannelMsg;

public interface GUIListener
{
   public boolean enqueueMsg( SharedSpace space, ChannelMsg channelMsg);
}
