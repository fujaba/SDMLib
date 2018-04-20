package org.sdmlib.test.examples.modelspace.chat;

import java.io.File;

import org.junit.Test;
import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelCreator;

import de.uniks.networkparser.IdMap;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MSChatClientTest
{
   private IdMap idMap;
   private MSChatChannel channel;
   private String sessionId;
   private String userName;
   private String channelName;
   private ModelSpace space;
   private Storyboard story;

     /**
    * 
    * @see <a href='../../../../../../../../../doc/DirectChat.html'>DirectChat.html</a>
*/
   // @Test model space shall be deleted AZ
   public void testDirectChat() throws Exception
   {
      // System.out.println("Testing model space");
	   if(TestUtil.isTravis()) {
		   return;
	   }

      story = new Storyboard();
      
      // remove old json files
      File file = new File("modeldata/ZuenFamily");
      
      if (file.exists() && file.isDirectory())
      {
         for (File f : file.listFiles())
         {
            if ( ! f.getName().endsWith(".gitignore"))
            {
               f.delete();
            }
         }
      }
      
      
      // open model space and store first task
      channelName = "ZuenFamily";
      userName = "testDirectChat";
      
      sessionId = userName + System.currentTimeMillis();

      idMap = MSChatChannelCreator.createIdMap(sessionId);
      
      channel = new MSChatChannel();
      
      
      idMap.put(channelName, channel);
      
      space = new ModelSpace(idMap, userName).open("modeldata/" + channelName);
      
      channel.withTask("Albert sendready");
      
      // try to create two client apps
      Thread firstClient = new Thread()
      {
         @Override
         public void run()
         {
            MSChatClient.main("ZuenFamily", "Albert");
         }
      };
      
      firstClient.start();
      
      boolean done = false;
      while ( ! done)
      {
         // read changes of others, maybe react
         String buf = space.changeQueue.take(); // .poll(30, TimeUnit.SECONDS);
         
         if (buf == null) 
         {
//            System.out.println("time out waiting for change: ");
            story.add("time out waiting for change: ");
            break;
         }
         
         space.readChanges(buf);
         
         switch (channel.getTask())
         {
         case "testDirectChat Albert is ready":
            // start Sabine client
            channel.withTask("Sabine sendready");
            
            Platform.runLater(new Runnable()
            {
               
               @Override
               public void run()
               {
                  MSChatClient sabineClient = new MSChatClient("ZuenFamily", "Sabine");
                  Stage newStage = new Stage();
                  try
                  {
                     sabineClient.start(newStage);
                  }
                  catch (Exception e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
                  
               }
            });
            
            break;
            
         
         case "testDirectChat Sabine is ready":
            // start Sabine client
            done = true;
            break;
            

         case "testDirectChat exit":
            done = true;
            break;

         default:
            break;
         }         
      }
      
      MSChatMsg msg = new MSChatMsg().withSender(userName).withText("Thank you for your cooperation");
      
      channel.withMsgs(msg);
      
      story.addObjectDiagram(channel);

      story.dumpHTML();
      
//      System.out.println("Testing model Space done");
      
      // Platform.exit();
      // System.exit(0);
   }
}
