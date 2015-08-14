package org.sdmlib.test.examples.groupaccount.gui;

import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GroupAccountApp extends Application
{
   public static GroupAccountApp theApp = null; 
   private GroupAccount dataRoot;
   
   public GroupAccount getDataRoot()
   {
      return dataRoot;
   }
   
   private GroupAccountController groupAccountController;

   public GroupAccountController getGroupAccountController()
   {
      return groupAccountController;
   }
   
   private Group guiRoot;
   
   public Group getGuiRoot()
   {
      return guiRoot;
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception
   {
      System.out.println("starting");
      theApp = this;
      
      ScrollPane scrollPane = buildRootNode();
      
      Scene scene = new Scene(scrollPane, 450, 700);
      
      stage.setTitle("Group Account");
      
      stage.setScene(scene);
      
      stage.show();
   }

   public ScrollPane buildRootNode()
   {
      dataRoot = new GroupAccount();
      
      Item dummyItem = dataRoot.createItem();
      
      Person dummyPerson = dataRoot.createPersons().withItem(dummyItem);
      
      guiRoot = new Group();

      ScrollPane scrollPane = new ScrollPane(guiRoot);
      
      scrollPane.setStyle("-fx-background: white;");
      

      groupAccountController = new GroupAccountController(dataRoot, guiRoot);
      return scrollPane;
   }

}
