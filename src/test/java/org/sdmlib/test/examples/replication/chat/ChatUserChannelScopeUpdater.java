package org.sdmlib.test.examples.replication.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.SeppelSpaceProxy;

public class ChatUserChannelScopeUpdater implements PropertyChangeListener
{

   private SeppelSpaceProxy selfProxy;
   private SeppelSpace seppelSpace;

   public ChatUserChannelScopeUpdater(SeppelSpace seppelSpace, SeppelSpaceProxy selfProxy)
   {
      this.seppelSpace = seppelSpace;
      this.selfProxy = selfProxy;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null)
      {
         boolean oldFlag = seppelSpace.isApplyingChangeMsg();
         seppelSpace.setApplyingChangeMsg(false);
         
         try
         {
            // new channel, provide scope to proxy
            ChatChannel channel = (ChatChannel) evt.getNewValue();
            
            // find scope
            SeppelScope scope = selfProxy.getScopes().hasObservedObjects(channel).first();
            
            // find user proxy
            ChatUser user = (ChatUser) evt.getSource();
            
            SeppelSpaceProxy proxy = selfProxy.getPartners().hasLoginName(user.getUserName()).first();
            
            proxy.withScopes(scope);
            
            // resend all changes in order to send scope content
            seppelSpace.sendAllChanges(proxy.getChannel());
         }
         finally
         {
            seppelSpace.setApplyingChangeMsg(oldFlag);
         }
      }
   }
}
