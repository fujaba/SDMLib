package org.sdmlib.test.examples.replication.chat;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.SeppelBoardTaskAction;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSocketAcceptThread;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.SeppelTaskHandler;
import org.sdmlib.replication.util.SeppelSpaceProxyCreator;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.util.StoryboardCreator;
import org.sdmlib.test.examples.replication.chat.util.ChatRootCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class ReplicationChatServer 
{

   private int serverPort;
   private ChatRoot chatRoot;
   private SeppelSpaceProxy selfProxy;
   private SeppelSpace seppelSpace;

   public static void main(String[] args)
   {
      ReplicationChatServer replicationServer = new ReplicationChatServer().init(args);
   }

   private ReplicationChatServer init(String[] args)
   {
      // open shared space and load data
      String serverName = args[0];
      JsonIdMap idMap = new ChatRootCreator().createIdMap(serverName);
      idMap.withCreator(StoryboardCreator.createIdMap(serverName));
      seppelSpace = new SeppelSpace().init(idMap, false, args[1], Integer.parseInt(args[2]));
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

      addReplicationChatTaskHandler();

      // open server socket allowing clients to connect
      serverPort = Integer.parseInt(args[2]);
      SeppelSocketAcceptThread seppelSocketAcceptThread = new SeppelSocketAcceptThread(seppelSpace, serverPort);
      seppelSocketAcceptThread.start();


      return this;
   }

   private void addReplicationChatTaskHandler()
   {
      seppelSpace.withTaskHandler(
         new SeppelTaskHandler()
         .with("addAllProxiesToCmdScope", new SeppelBoardTaskAction() 
         {
            @Override
            public void run(BoardTask task)
            {
               SeppelSpaceProxy testerProxy =  (SeppelSpaceProxy) task.getFromTaskObjects("selfProxy");

               if (testerProxy != null)
               {
                  SeppelScope cmdScope = testerProxy.getScopes().hasScopeName("commands").first();

                  // add tom and sabine to cmdScope
                  for (SeppelSpaceProxy proxy : selfProxy.getPartners())
                  {
                     proxy.withScopes(cmdScope);
                     
                     if (proxy.getChannel() != null)
                     {
                        seppelSpace.sendAllChanges(proxy.getChannel());
                     }
                  }

                  // protocol result
                  Storyboard story = (Storyboard) task.getFromTaskObjects("story");
                  
                  story.withJsonIdMap(SeppelSpaceProxyCreator.createIdMap("sb"));
                  
                  story.addObjectDiagramWith(selfProxy, selfProxy.getPartners(), 
                     selfProxy.getPartners().getTasks(), task);
                  
                  cmdScope.withObservedObjects(story.getStoryboardSteps().last());
                  
                  // ask tom to chat with sabine
                  BoardTask boardTask = cmdScope.add(
                     new BoardTask().withName("tomChatWithSabine")
                     .withTaskObject("story", story));
                  
                  task.withNext(boardTask);

                  SeppelSpaceProxy tomProxy = selfProxy.getPartners().hasLoginName("tom").first();
                  tomProxy.withTasks(boardTask);
               }
            }

         })
         .with("sabineCheckInbox", new SeppelBoardTaskAction() 
         {
            @Override
            public void run(BoardTask task)
            {

               // ask the server to ask sabine to check the inbox
               SeppelSpaceProxy sabineProxy = selfProxy.getPartners().hasLoginName("sabine").first();
               SeppelScope cmdScope = selfProxy.getScopes().hasScopeName("commands").first();

               sabineProxy.withTasks(task);
            }
         })
         .with("terminate", new SeppelBoardTaskAction() 
         {
            @Override
            public void run(BoardTask task)
            {

               // ask the server to ask sabine to check the inbox
               SeppelSpaceProxy proxy = selfProxy.getPartners().hasLoginName("tester").first();
               SeppelScope cmdScope = selfProxy.getScopes().hasScopeName("commands").first();

               proxy.withTasks(task);
            }
         })
         .with(Exception.class.getName(), new SeppelBoardTaskAction() 
         {
               @Override
               public void run(BoardTask task)
               {
                  // if there is a tester inform it
                  SeppelSpaceProxy proxy = selfProxy.getPartners().hasLoginName("tester").first();
                  SeppelScope cmdScope = selfProxy.getScopes().hasScopeName("commands").first();

                  proxy.withTasks(task);
                  
               }
            
         }));

   }

}
