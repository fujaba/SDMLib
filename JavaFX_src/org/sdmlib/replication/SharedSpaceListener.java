package org.sdmlib.replication;

import javafx.application.Platform;

import org.sdmlib.replication.SharedSpace.ChannelMsg;

public class SharedSpaceListener implements GUIListener
{
   @Override
   public boolean enqueueMsg( SharedSpace space, ChannelMsg channelMsg){
      Platform.runLater(new JavaFXMsgHandler(space, channelMsg));
      return true;
   }
}
