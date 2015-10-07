package org.sdmlib.test.examples.groupaccount.gui;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.GroupAccountCreator;
import org.sdmlib.test.examples.modelspace.chat.MSChatClient;

import de.uniks.networkparser.json.JsonIdMap;

public class GroupAccountAppSimpleTest
{
   LinkedBlockingQueue<Object> testResults = new LinkedBlockingQueue<>();
   private JsonIdMap idMap;
   private GroupAccount dataRoot;
   private ModelSpace space;
   
   
   
   @Test
   public void testGroupAccountMultiUserGui() throws InterruptedException
   {
      Storyboard story = new Storyboard();
      
      // clean chat directory from .jsonchgs
      final String location = "modeldata/groupaccount/junitest";
      
      File file = new File(location);
      
      if (file.exists() && file.isDirectory())
      {
         for (File f : file.listFiles())
         {
            if ( f.getName().endsWith(".jsonchgs"))
            {
               f.delete();
            }
         }
      }
       
      String userName = "junit";
      // add model space
      idMap = GroupAccountCreator.createIdMap(userName + System.currentTimeMillis());
      
      dataRoot = new GroupAccount();
      
      dataRoot.updateBalances();
      
      idMap.put("dataRoot", dataRoot);
      
      space = new ModelSpace(idMap, userName).open(location );
      
      story.addStep("Albert buys beer for 12 Euro");

      dataRoot.setTask("Albert buy beer");
      
      // first try whether javafx is already running
      try
      {
         Platform.runLater(new Runnable()
         {
            @Override
            public void run()
            {
               Stage stage = new Stage();
               GroupAccountApp groupAccountApp = new GroupAccountApp(location, "Albert");
               try
               {
                  groupAccountApp.start(stage);
               }
               catch (Exception e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            }
         });
      }
      catch (Exception e1)
      {
         // TODO Auto-generated catch block
         System.out.println("Starting via main. ");
         // start grouptaccount app for albert
         Thread firstClient = new Thread()
         {
            @Override
            public void run()
            {
               try
               {
                  GroupAccountApp.main(location, "Albert");
               }
               catch (Exception e)
               {
                  // probably the platform is alread running
                  // just call start and we are done
                  System.out.println("CAUTION: GroupAccountApp.main did not start");
                  
               }
            }
         };
         
         firstClient.start();
      }
      
      
      
      boolean done = false;
      while ( ! done)
      {
         // read changes of others, maybe react
         String buf = space.changeQueue.take(); // changeQueue.poll(5, TimeUnit.SECONDS); // changeQueue.take(); // ;
         
         if (buf == null) 
         {
            System.out.println("time out waiting for change: ");
            story.add("time out waiting for change: ");
            Assert.fail();
            break;
         }
         
         space.readChanges(buf);
         
         if (dataRoot.getTask().startsWith("Test start Sabine"))
         {
            story.addImage("GroupAccountAlbertBuysBeerImage.png");
            
            Person albert = dataRoot.getPersons().first();
            
            Item beer = albert.getItem().first();
            
            albert.createItem().withDescription("Beef").withValue(42);
            
            beer.removeYou();
            
            albert.createItem().withDescription("Coal").withValue(7);
            
            done = true;  
         }
      }
      
      story.addObjectDiagram(dataRoot);
      
      Thread.sleep(3000);
      
      // Platform.exit();
      
      story.dumpHTML();
      
      System.out.println("done");
      
   }
   
   // @Test
   public void testGroupAccountGui()
   {
      new Thread() {
         @Override
         public void run()
         {
            GroupAccountApp.main(null);
         }
         
      }.start();
      
      
      
      try
      {
         Thread.sleep(3000);
      }
      catch (InterruptedException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      Platform.runLater(new Runnable()
      {
         
         @Override
         public void run()
         {
            // set first person name to albert
            GroupAccountController groupAccountController = GroupAccountApp.theApp.getGroupAccountController();
            PersonController personController = groupAccountController.getPersonControllers().get(groupAccountController.getDataRoot().getPersons().first());
            personController.getNameField().setText("Albert");
            LinkedHashMap<Item, ItemController> itemControllers = personController.getItemControllers();
            Item firstItem = itemControllers.keySet().iterator().next();
            ItemController itemController = itemControllers.get(firstItem);
            
            itemController.getTextField().setText("Beer"); 
            
            assertEquals("Name of first person should be:", "Albert", personController.getPerson().getName());
            // goto app and press add person button 
            
            try
            {
               testResults.put("done");
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      });
      
      
      try
      {
         testResults.take();
      }
      catch (InterruptedException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
