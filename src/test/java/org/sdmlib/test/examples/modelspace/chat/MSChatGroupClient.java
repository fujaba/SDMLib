package org.sdmlib.test.examples.modelspace.chat;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatGroupCreator;

import de.uniks.networkparser.IdMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MSChatGroupClient  extends Application
{
   public MSChatGroupClient()
   {

   }

   public MSChatGroupClient(String location, String userName)
   {
      this.location = location;
      this.userName = userName;
   }

   public static void main(String... args)
   {
      launch(args);
   }

   private Stage stage;
   private String userName = null;
   private String location = null;
   private IdMap idMap;
   private MSChatGroup group;
   private ModelSpace space;
   private String sessionId;
   private TextField chatChannelInput;
   private Button chatChannelButton;
   private VBox channelList;

   
   public TextField getChatChannelInput()
   {
      return chatChannelInput;
   }
   

   public MSChatGroup getGroup()
   {
      return group;
   }


   public String getUserName()
   {
      return userName;
   }


   @Override
   public void start(Stage stage) throws Exception
   {
      this.stage = stage;

      if (location == null)
      {
         List<String> parameters = this.getParameters().getRaw();
         location = parameters.get(0);
         userName = parameters.get(1);
      }

      sessionId = userName + System.currentTimeMillis();

      idMap = MSChatGroupCreator.createIdMap(sessionId);

      group = new MSChatGroup();

      idMap.put(location, group);

      space = new ModelSpace(idMap, userName, ApplicationType.JavaFX).open("modeldata/" + location);

      // build gui
      channelList = new ListController().init(idMap, this, group, MSChatGroup.PROPERTY_CHANNELS, ChannelDescriptionController.class);

      chatChannelInput = new TextField();
      chatChannelInput.setPrefWidth(192);
      chatChannelButton = new Button("Send");

      chatChannelButton.setOnAction(new ChatChannelButtonAction(this));

      HBox chatInputLine = new HBox(chatChannelInput, chatChannelButton);
      chatInputLine.setPadding(new Insets(8, 0, 8, 8));
      chatInputLine.setSpacing(8);
      HBox.setHgrow(chatInputLine, Priority.ALWAYS);

      VBox root = new VBox(channelList, chatInputLine);
      root.setPadding(new Insets(25));
      
      Scene scene = new Scene(root, 300, 600);
      stage.setScene(scene);
      stage.setTitle("Model Space Group: " + userName + " " + location);


      //    group.getPropertyChangeSupport().addPropertyChangeListener(MSChatChannel.PROPERTY_TASK, 
      //         new PropertyChangeListener()
      //         {
      //            @Override
      //            public void propertyChange(final PropertyChangeEvent evt)
      //            {
      //               Platform.runLater(new Runnable()
      //               {
      //                  @Override
      //                  public void run()
      //                  {
      //                     handleTaskChange(evt);
      //                  }
      //               });
      //            }
      //         });

      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
         {

         @Override
         public void handle(WindowEvent arg0)
         {
            Platform.exit();
            System.exit(0);
         }
         });

      this.stage.show();

      handleTaskChange(null);
   }

   public void handleTaskChange(PropertyChangeEvent evt)
   {
      String newTask = group.getTask();

      if (evt != null) 
      {
         newTask = (String) evt.getNewValue();
      }

      if (newTask == null) return;

      if (newTask.equals(userName + " sendready"))
      {
         group.setTask("testChatGroup " + userName + " is ready");
      }
   }

   public void openChannel(String text)
   {
      MSChatClient chatClient = new MSChatClient(text, userName);
      Stage newStage = new Stage();
      try
      {
         chatClient.start(newStage);
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
}
