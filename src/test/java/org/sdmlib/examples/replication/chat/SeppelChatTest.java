package org.sdmlib.examples.replication.chat;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;
import org.sdmlib.examples.replication.chat.util.ChatRootCreator;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.JVMWraper;
import org.sdmlib.replication.SeppelBoardTaskAction;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.SeppelTaskHandler;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.util.StoryboardCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class SeppelChatTest
{
   private SeppelSpace seppelSpace;
   private SeppelSpaceProxy selfProxy;
   private SeppelSpaceProxy partner;

   @Test
   public void testSeppelChat()
   {
      Storyboard story = new Storyboard();

      story.addStep("Test a family chat server and an SE chat server and clients attached to one of those or to both.");
      
      story.addStep("First clean old json data");
      
      File sharedSpaceDir = new File("SharedSpace");
      
      if (sharedSpaceDir.exists() && sharedSpaceDir.isDirectory())
      {
         for (File f : sharedSpaceDir.listFiles())
         {
            f.delete();
         }
      }
      
      story.addStep("start the two servers");
      
      String javaHome = System.getenv().get("PROGRAMFILES");
      
      JVMWraper vm = new JVMWraper();
      
      vm.runJava("10113", ReplicationChatServer.class.getName(), "zuenFamilyChatServer localhost 11142 albert admin sabine admin tom admin tester admin");

      // vm.runJava("10114", ReplicationChatServer.class.getName(), "seChatServer localhost 11144 albert admin nina admin stefan admin tester admin");
      
      story.addStep("start three or four clients");
      
      vm.runJava("10115", ReplicationChatClientApp.class.getName(), "sabine admin zuenFamilyChatServer localhost 11142");
      vm.runJava("10116", ReplicationChatClientApp.class.getName(), "tom admin zuenFamilyChatServer localhost 11142");
      
      story.addStep("Tom opens channel with Sabine and sends a message via the family server not seen by Albert.");

      final LinkedBlockingQueue<BoardTask> inbox = new LinkedBlockingQueue<BoardTask>();
      
      // open my own seppelspace and login to zuenFamilyChatServer
      JsonIdMap idMap = ChatRootCreator.createIdMap("tester");
      idMap.withCreator(StoryboardCreator.createIdMap("tester"));
      seppelSpace = new SeppelSpace().init(idMap, false, null, 0);
      
      // do not load old data

      // add a scope for command objects
      SeppelScope cmdScope = new SeppelScope().withScopeName("commands");
      
      selfProxy = seppelSpace.getSelfProxy();
      
      selfProxy.withScopes(cmdScope); 

      // login to servers
      SeppelSpaceProxy zuenFamilyChatServerProxy = seppelSpace.connectTo("zuenFamilyChatServer", "localhost", 11142, "tester", "admin", cmdScope);
      
      zuenFamilyChatServerProxy.withScopes(cmdScope);
      
      cmdScope.withObservedObjects(story);
      cmdScope.withObservedObjects(story.getStoryboardSteps().toArray());
      
      BoardTask boardTask = cmdScope.add(
         new BoardTask()
         .withName("addAllProxiesToCmdScope")
         .withTaskObject("selfProxy", selfProxy)
         .withTaskObject("story", story));

      zuenFamilyChatServerProxy.withTasks(boardTask);
      
      seppelSpace.withTaskHandler(
         new SeppelTaskHandler()
         .with("terminate", new SeppelBoardTaskAction() 
         {
               @Override
               public void run(BoardTask task)
               {
                  inbox.offer(task);
               }
            
         })
         .with(Exception.class.getName(), new SeppelBoardTaskAction() 
         {
               @Override
               public void run(BoardTask task)
               {
                  inbox.offer(task);
               }
            
         }));
            
      
      seppelSpace.start();

      try
      {
         BoardTask task = inbox.take(); // wait for handler to terminate test
         
         vm.killSubProcesses();

         story.assertTrue("Normal termination", "terminate".equals(task.getName()));
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      
      
      story.dumpHTML();
   }
}
