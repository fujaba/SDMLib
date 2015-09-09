package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.GroupAccountCreator;

import de.uniks.networkparser.json.JsonIdMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GroupAccountApp extends Application
{
   public static GroupAccountApp theApp = null; 
   private GroupAccount dataRoot;
   
   public GroupAccountApp(String location2, String userName2)
   {
      this.location = location2;
      this.userName = userName2;
   }
   
   public GroupAccountApp()
   {
      // empty
   }

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
   private Scene scene;
   
   public Group getGuiRoot()
   {
      return guiRoot;
   }

   public static void main(String... args)
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
         List<String> parameters = params.getRaw();
         if (parameters.size() >= 2)
         {
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
            
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            
            userName = hostname;
         }
      }

      
      ScrollPane scrollPane = buildRootNode();
      
      scene = new Scene(scrollPane, 450, 700);
      
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
      idMap = GroupAccountCreator.createIdMap(userName + System.currentTimeMillis());
      
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
      
      PropertyChangeListener listener = new PropertyChangeListener()
      {
         
         @Override
         public void propertyChange(PropertyChangeEvent evt)
         {
            // execute tasks
            if (dataRoot.getTask().equals(userName + " buy beer"))
            {
               Person firstPerson = dataRoot.getPersons().first();
               firstPerson.setName(userName);
               Item firstItem = firstPerson.getItem().first();
               firstItem.withDescription("Beer").withValue(12);
               
               Platform.runLater(new Runnable()
               {
                  
                  @Override
                  public void run()
                  {
                     double guiRootSize = dataRoot.getTotalPurchase();
                     
                     Path sizeFilePath = Paths.get("doc/GroupAccountAlbertBuysBeerImage.size");
                     
                     try
                     {
                        List<String> readAllLines = Files.readAllLines(sizeFilePath);
                        String line = readAllLines.get(0);
                        if (line.equals(""+guiRootSize))
                        {
                           // do not write a new image
                           return;
                        }
                     }
                     catch (IOException e)
                     {
                        // do new screenshot
                     }
                     
                     try
                     {
                        Files.write(sizeFilePath, (""+guiRootSize).getBytes());
                     }
                     catch (IOException e)
                     {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     
                     WritableImage snapshot = scene.snapshot(null);
                     
                     File file = new File("doc/GroupAccountAlbertBuysBeerImage.png");
                     
                     try {
                        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
                    } catch (Exception s) {
                    }
                  }
               });

               dataRoot.setTask("Test start Sabine");
            }
            
         }
      };
      
      dataRoot.getPropertyChangeSupport().addPropertyChangeListener(GroupAccount.PROPERTY_TASK, listener);
      
      listener.propertyChange(null);
      
      return scrollPane;
   }

}
