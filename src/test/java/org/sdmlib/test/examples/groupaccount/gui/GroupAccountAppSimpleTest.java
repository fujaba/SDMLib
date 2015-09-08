package org.sdmlib.test.examples.groupaccount.gui;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.model.Item;

public class GroupAccountAppSimpleTest
{
   LinkedBlockingQueue<Object> testResults = new LinkedBlockingQueue<>();
   
   
   
   @Test
   public void testGroupAccountMultiUserGui()
   {
      Storyboard story = new Storyboard();
      
      
      
      story.dumpHTML();
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
