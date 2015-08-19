package org.sdmlib.test.examples.groupaccount.gui;

import java.util.List;

import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.GroupAccountCreator;

import de.uniks.networkparser.json.JsonIdMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
   private JsonIdMap idMap;
   private ModelSpace space;
   private String location;
   private String userName;
   private String cloudLocation;
   
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
      
      cloudLocation = "modeldata";
      
      if (location == null)
      {
         Parameters params = this.getParameters();
         if (params != null)
         {
            List<String> parameters = params.getRaw();
            location = parameters.get(0);
            userName = parameters.get(1);
            
            if (parameters.size() >= 3)
            {
               cloudLocation = parameters.get(2);
            }
         }
         else
         {
            location = cloudLocation + "/groupaccount/test";
            userName = "dummy";
         }
      }

      
      ScrollPane scrollPane = buildRootNode();
      
      Scene scene = new Scene(scrollPane, 450, 700);
      
      stage.setTitle("Group Account " + userName);
      
      stage.setScene(scene);
      
      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
         {

         @Override
         public void handle(WindowEvent arg0)
         {
            Platform.exit();
            System.exit(0);
         }
         });
      
      stage.show();
   }

   public ScrollPane buildRootNode()
   {
      // add model space
      idMap = GroupAccountCreator.createIdMap(userName);
      
      dataRoot = new GroupAccount();
      
      idMap.put("dataRoot", dataRoot);
      
      space = new ModelSpace(idMap, userName, ApplicationType.JavaFX).open(location);

      if (dataRoot.getPersons().isEmpty())
      {
         Person dummyPerson = dataRoot.createPersons();
         dummyPerson.createItem();
      }
      
      guiRoot = new Group();
         
      ScrollPane scrollPane = new ScrollPane(guiRoot);
      
      scrollPane.setStyle("-fx-background: white;");
      

      groupAccountController = new GroupAccountController(dataRoot, guiRoot);
      return scrollPane;
   }

}
