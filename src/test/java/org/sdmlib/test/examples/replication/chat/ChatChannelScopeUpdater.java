package org.sdmlib.test.examples.replication.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatChannelScopeUpdater implements PropertyChangeListener
{

   private SeppelScope scope;

   public ChatChannelScopeUpdater(SeppelScope scope)
   {
      this.scope = scope;
      
   }

   @Override
   public void propertyChange(PropertyChangeEvent event)
   {
      if (event.getNewValue() != null)
      {
         // new message in this channel, add to scope
         scope.withObservedObjects(event.getNewValue());
      }
   }

}
