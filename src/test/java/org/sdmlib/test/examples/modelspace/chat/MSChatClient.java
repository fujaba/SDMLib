package org.sdmlib.test.examples.modelspace.chat;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.sdmlib.modelspace.ModelSpace;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class MSChatClient  extends Application
{
   /**
    * Launch the application.
    * @param args
    */
   public static void main(String[] args)
   {
      launch(args);
   }

   private Stage stage;
   private String userName;
   private TextField chatInput;
   private Button chatButton;
   private Text chatText;
   private String channelName;
   private JsonIdMap idMap;
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
      
      List<String> parameters = this.getParameters().getRaw();
      
      channelName = parameters.get(0);
      userName = parameters.get(1);
      
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

      this.stage.show();
   }
}
