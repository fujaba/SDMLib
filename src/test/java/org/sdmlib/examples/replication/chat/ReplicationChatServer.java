package org.sdmlib.examples.replication.chat;

import org.sdmlib.examples.replication.chat.util.ChatRootCreator;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSocketAcceptThread;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.SeppelSpaceProxy;

public class ReplicationChatServer 
{
   
   private int serverPort;
   private ChatRoot chatRoot;
   private SeppelSpaceProxy selfProxy;

   public static void main(String[] args)
   {
      ReplicationChatServer replicationServer = new ReplicationChatServer().init(args);
   }

   private ReplicationChatServer init(String[] args)
   {
      // open shared space and load data
      String serverName = args[0];
      SeppelSpace seppelSpace = new SeppelSpace().init(new ChatRootCreator().createIdMap(serverName), false, args[1], Integer.parseInt(args[2]));
      seppelSpace.start();
      
      selfProxy = seppelSpace.getSelfProxy();
      
      chatRoot = new ChatRoot();
      seppelSpace.put("chatRoot", chatRoot);
      

      seppelSpace.loadHistoryFromFile();
      
      SeppelScope listOfUsersScope = selfProxy.getScopes().hasScopeName("listOfUsersScope4"+serverName).first();
      
      if (listOfUsersScope == null)
      {
         listOfUsersScope = new SeppelScope();
         seppelSpace.put("listOfUsersScope4"+serverName, listOfUsersScope);
         listOfUsersScope.withScopeName("listOfUsersScope4"+serverName);
         selfProxy.withScopes(listOfUsersScope);
         listOfUsersScope.withObservedObjects(chatRoot);
      }
      

      // init users from args
      for (int i = 3; i + 1 < args.length; i += 2)
      {
            String userName = args[i];
            SeppelSpaceProxy partner = selfProxy.getPartners().hasLoginName(userName).first();
            
            if (partner == null)
            {
               SeppelSpaceProxy newProxy = new SeppelSpaceProxy();
               seppelSpace.put(userName+"Proxy", newProxy);
               newProxy.withScopes(listOfUsersScope);
               partner = newProxy.withLoginName(userName).withPassword(args[i+1]).withPartners(selfProxy);
            }
            
            ChatUser chatUser = chatRoot.getUsers().hasUserName(userName).first();
            
            if (chatUser == null)
            {
               // create 
               chatUser = new ChatUser();
               seppelSpace.put("ChatUser."+userName, chatUser);
               chatUser.setUserName(userName);
               chatRoot.withUsers(chatUser);
            }
            
            listOfUsersScope.withObservedObjects(chatUser);
      }
      
      // share the list of users with every partner
      selfProxy.getPartners().withScopes(listOfUsersScope);
      
      // subscribe at ChatUsers for new channels, if a user has a channel, it should also share the corresponding scope
      ChatUserChannelScopeUpdater updater = new ChatUserChannelScopeUpdater(seppelSpace, selfProxy);
      for (ChatUser user : chatRoot.getUsers())
      {
         user.getPropertyChangeSupport().addPropertyChangeListener(ChatUser.PROPERTY_CHANNELS, updater);
      }
      
      // open server socket allowing clients to connect
      serverPort = Integer.parseInt(args[2]);
      SeppelSocketAcceptThread seppelSocketAcceptThread = new SeppelSocketAcceptThread(seppelSpace, serverPort);
      seppelSocketAcceptThread.start();
      
      
      return this;
   }

}
