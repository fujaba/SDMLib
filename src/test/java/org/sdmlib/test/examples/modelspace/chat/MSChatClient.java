package org.sdmlib.test.examples.modelspace.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelCreator;

import de.uniks.networkparser.IdMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MSChatClient  extends Application
{
   public MSChatClient()
   {
      
   }
   
   public MSChatClient(String channelName, String userName)
   {
      this.channelName = channelName;
      this.userName = userName;
   }
   
   public static void main(String... args)
   {
      launch(args);
   }

   private Stage stage;
   private String userName = null;
   private TextField chatInput;
   private Button chatButton;
   private Text chatText;
   private String channelName = null;
   private IdMap idMap;
   private MSChatChannel channel;
   private ModelSpace space;
   private String sessionId;
   
   
   public MSChatChannel getChannel()
   {
      return channel;
   }
   
   
   public TextField getChatInput()
   {
      return chatInput;
   }
   
   
   public Text getChatText()
   {
      return chatText;
   }
   
   
   public String getUserName()
   {
      return userName;
   }
   
   
   @Override
   public void start(Stage stage) throws Exception
   {
      this.stage = stage;
      
      if (channelName == null)
      {
         List<String> parameters = this.getParameters().getRaw();
         channelName = parameters.get(0);
         userName = parameters.get(1);
      }
      
      sessionId = userName + System.currentTimeMillis();

      idMap = MSChatChannelCreator.createIdMap(sessionId);
      
      channel = new MSChatChannel();
      
      idMap.put(channelName, channel);
      
      space = new ModelSpace(idMap, userName, ApplicationType.JavaFX).open("modeldata/" + channelName);
      
      // build gui
      chatText = new Text("Hello\nWorld\n ");
      
      if (! channel.getMsgs().isEmpty())
      {
         chatText.setText(channel.getMsgs().toString("\n"));
      }
      
      channel.addPropertyChangeListener(new ChatTextUpdater(this, channel));
      channel.getPropertyChangeSupport().addPropertyChangeListener(MSChatChannel.PROPERTY_TASK, 
         new PropertyChangeListener()
         {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
               Platform.runLater(new Runnable()
               {
                  @Override
                  public void run()
                  {
                     handleTaskChange(evt);
                  }
               });
            }
         });
      
      ScrollPane scrollPane = new ScrollPane(chatText);
      scrollPane.setPrefHeight(400);
      
      chatInput = new TextField();
      chatInput.setPrefWidth(192);
      chatButton = new Button("Send");
      
      chatButton.setOnAction(new ChatButtonAction(this));
      
      HBox chatInputLine = new HBox(chatInput, chatButton);
      chatInputLine.setPadding(new Insets(8, 0, 8, 8));
      chatInputLine.setSpacing(8);
      HBox.setHgrow(chatInputLine, Priority.ALWAYS);
      
      VBox root = new VBox(chatText, chatInputLine);
      root.setPadding(new Insets(25));
      root.setMargin(scrollPane, new Insets(8,0,0,8));
      
      Scene scene = new Scene(root, 300, 600);
      stage.setScene(scene);
      stage.setTitle("Model Space Chat: " + userName + " " + channelName);
      
      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
      {
         
         @Override
         public void handle(WindowEvent arg0)
         {
            Platform.exit();
            System.exit(0);
         }
      });
      
      ObservableList<Screen> screens = Screen.getScreens();
      
      Screen screen = screens.get(0);
      
      if (screens.size() > 1)
      {
         screen = screens.get(1);
      }
      
      Rectangle2D bounds = screen.getBounds();

      
      stage.setX(bounds.getMinX());
      stage.setY(0);

      this.stage.show();
      
      handleTaskChange(null);
   }
   
   public void handleTaskChange(PropertyChangeEvent evt)
   {
      String newTask = channel.getTask();
            
      if (evt != null) 
      {
         newTask = (String) evt.getNewValue();
      }
      
      if (newTask == null) return;
      
      if (newTask.equals(userName + " sendready"))
      {
         channel.setTask("testDirectChat " + userName + " is ready");
      }
   }
}
