package org.sdmlib.test.examples.groupaccount;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.gui.PartyAccountApp;

import org.testfx.api.*;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestPartyAccount extends ApplicationTest
{

   private PartyAccountApp partyAccountApp;
   private VBox root;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      Path path = Paths.get("aStore/TermStart.abu.yaml");
      if (Files.exists(path)) Files.delete(path);
      path = Paths.get("aStore/TermStart.abu.yaml.log");
      if (Files.exists(path)) Files.delete(path);

      partyAccountApp = new PartyAccountApp().withUserName("abu");
      partyAccountApp.start(primaryStage);
   }


   @Test
   public void testGroupAccountYamlPersistence() throws InterruptedException
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.addStep("Start a Party GUI and load changes from file");

      story.addStep("do some editing and log changes to file");

      story.addStep("stop gui");

      story.addStep("restart and load file with changes");

      story.addStep("compress changes");

      story.dumpHTML();
   }




   /**
    * 
    * @see <a href='../../../../../../doc/Party.html'>Party.html</a>
 * @see <a href='../../../../../../../../doc/Party.html'>Party.html</a>
 */
   @Test
   public void testParty() throws Exception
   {
      Storyboard story = new Storyboard();
      
      write("X Mas Party SE 2017");
      
      story.assertEquals("party name in logic", "X Mas Party SE 2017", 
         partyAccountApp.getParty().getPartyName());
      
      partyAccountApp.getParty().setPartyName("Ho Ho Ho: X Mas Party SE 2017");

      clickOn("#addPersonButton");
      
      write("Albert\t");
      
      type(KeyCode.SPACE);
      
      write("Beer\t");

      write("23\t");
      
      // check model
      double price = partyAccountApp.getParty().getGuests().getItems().getPrice().sum();
      
      story.assertEquals("price of first item:", 23.0, price);

      type(KeyCode.SPACE);
      
      write("Tacos\t");

      write("7\t");
      
      clickOn("#addPersonButton");
      
      write("Nina\t");
      
      type(KeyCode.SPACE);
      
      write("Meat\t");

      write("16\t");
      
      Platform.runLater(() -> saveAsPng(story, partyAccountApp.root, "partyDone"));
      
      // wait for save 
      String msg = testInbox.take();
      
      story.dumpHTML();
   }
   
   private LinkedBlockingQueue<String> testInbox = new LinkedBlockingQueue<String>();
   
   public void saveAsPng(Storyboard story, Node node, String snapName) 
   {
      int snapNum = 1;
      
      WritableImage image = node.snapshot(new SnapshotParameters(), null);

      // TODO: probably use a file chooser here
      String pathname = snapName + ".png";
      File file = new File("doc/" + pathname);
      snapNum++;

      try {
          ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
          
          story.addImage(pathname);
          
      } catch (IOException e) 
      {
         Logger.getGlobal().warning(e.toString());
      }

      testInbox.add("snapshot done");
      
  }
   
}
