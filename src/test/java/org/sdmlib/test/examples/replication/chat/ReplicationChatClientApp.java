package org.sdmlib.test.examples.replication.chat;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.SeppelBoardTaskAction;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.SeppelTaskHandler;
import org.sdmlib.replication.util.SeppelScopePO;
import org.sdmlib.replication.util.SeppelSpaceProxyPO;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.util.StoryboardCreator;
import org.sdmlib.test.examples.replication.chat.util.ChatChannelSet;
import org.sdmlib.test.examples.replication.chat.util.ChatRootCreator;
import org.sdmlib.test.examples.replication.chat.util.ChatUserCreator;
import org.sdmlib.test.examples.replication.chat.util.ChatUserPO;

import de.uniks.networkparser.json.JsonIdMap;


public class ReplicationChatClientApp extends Application
{
   private ChatRoot chatRoot;

   public ChatRoot getChatRoot()
   {
      return chatRoot;
   }
   
   private String userName;

   public String getUserName()
   {
      return userName;
   }
   
   private SeppelSpaceProxy selfProxy;
   private String pwd;
   private Text chatText;
   
   public Text getChatText()
   {
      return chatText;
   }
   
   private TextField chatInput;
   
   public TextField getChatInput()
   {
      return chatInput;
   }
   
   private ChatTextUpdater chatTextUpdater;
   private Stage stage;
   private ChannelListUpdater channelListUpdater;
   
   
   /**
    * Launch the application.
    * @param args
    */
   public static void main(String[] args)
   {
      launch(args);
   }

 

   @Override
   public void start(Stage stage) throws Exception
   {
      this.stage = stage;
      List<String> parameters = this.getParameters().getRaw();
      
      userName = parameters.get(0);
      pwd = parameters.get(1);
      
      JsonIdMap idMap = ChatRootCreator.createIdMap(userName);
      idMap.withCreator(StoryboardCreator.createIdMap(userName));
      seppelSpace = new SeppelSpace().init(idMap, true, null, 0);

      selfProxy = seppelSpace.getSelfProxy();
      
      chatRoot = new ChatRoot();
      seppelSpace.put("chatRoot", chatRoot);
      
      seppelSpace.loadHistoryFromFile();
      
      selfProxy.setLoginName(userName);
      selfProxy.setPassword(pwd);
      
      // get server spaces from parameters
      // albert admin zuenFamChatServer localhost 11142
      for (int i = 2; i < parameters.size(); i+=3)
      {
         String partnerName = parameters.get(i);
         SeppelSpaceProxy partner = selfProxy.getPartners().hasLoginName(parameters.get(i)).first();
         
         if (partner == null)
         {
            partner = new SeppelSpaceProxy();
            seppelSpace.put(partnerName+"Proxy", partner);
            partner.setLoginName(partnerName);
            selfProxy.withPartners(partner);
         }
         partner.setHostName(parameters.get(i+1));
         partner.setPortNo(Integer.parseInt(parameters.get(i+2)));
         
         // user and scope will be send by server
      } 
      
      // connect to server spaces if possible
      for (SeppelSpaceProxy proxy : selfProxy.getPartners())
      {
         try
         {
            SeppelChannel channel = proxy.getOrCreateChannel();
            channel.setSeppelSpace(seppelSpace);
            
            channel.start();
            
            channel.login();
            
            seppelSpace.sendAllChanges(channel);
         }
         catch (Exception e)
         {
            // might happen no big problem
            e.printStackTrace();
         }
      }
      
      
      // if there is a scope for some ChatChannel, add a listener that collects ChatMsgs in that scope
      for (SeppelScope scope : selfProxy.getScopes())
      {
         ChatChannelSet channelSet = scope.getObservedObjects().instanceOf(new ChatChannelSet());
         if ( ! channelSet.isEmpty())
         {
            ChatChannel channel = channelSet.first();
            channel.getPropertyChangeSupport().addPropertyChangeListener(ChatChannel.PROPERTY_MSGS, new ChatChannelScopeUpdater(scope));
         }
      }

      addReplicationChatTaskHandler();
      
      
      // build gui
      Label loginLabel = newLabel("Login: " + userName + " **** ");
      
      Label serversLabel = newLabel("Servers: ");
      
      Text serverList = new Text(selfProxy.getPartners().getLoginName().concat("\n"));
      VBox serverListVBox = new VBox(serverList);
      serverListVBox.setPadding(new Insets(0, 0, 8, 16));
      
      Label channelsLabel = newLabel("Channels: ");
      
      VBox channelListVBox = new VBox();
      channelListUpdater = new ChannelListUpdater(this, chatRoot, channelListVBox, userName);
      channelListVBox.setPadding(new Insets(0, 0, 8, 16));
      
      chatText = new Text("Hello\nWorld\n ");
      
      this.chatTextUpdater = new ChatTextUpdater(this);
      
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
      
      VBox root = new VBox(loginLabel, serversLabel, serverListVBox, channelsLabel, channelListVBox, scrollPane, chatInputLine);
      root.setPadding(new Insets(25));
      root.setMargin(scrollPane, new Insets(8,0,0,8));
      
      Scene scene = new Scene(root, 300, 600);
      stage.setScene(scene);
      stage.setTitle("Replication Chat: " + userName);
      stage.show();
   }

   private Insets labelInsets = new Insets(8);
   private ChatUser currentUser;
   private ChatUser selfUser;
   
   public ChatUser getSelfUser()
   {
      return selfUser;
   }
   
   private ChatChannel currentChannel;
   
   public ChatChannel getCurrentChannel()
   {
      return currentChannel;
   }
   
   private SeppelScope commonScope;
   private SeppelSpace seppelSpace;
   private Label newLabel(String string)
   {
      Label label = new Label(string);
      label.setPadding(labelInsets);
      
      return label;
   }

   @Override
   public void stop() throws Exception
   {
      super.stop();
      System.exit(0);
   }
   
   LinkedHashSet<ChatChannel> observedChannels = new LinkedHashSet<ChatChannel>();
   private Button chatButton;

   public void chatWithCurrentUser(ChatUser currentUser)
   {
      this.currentUser = currentUser;
      
      this.selfUser = chatRoot.getUsers().hasUserName(userName).first();
      
      currentChannel = currentUser.getChannels().intersection(selfUser.getChannels()).first();
      
      if (currentChannel == null)
      {
         String scopeName = selfUser.getUserName() + "_" + currentUser.getUserName();
         
         // sort alphabetically
         if (selfUser.getUserName().compareTo(currentUser.getUserName()) > 0)
         {
            scopeName = currentUser.getUserName()  + "_" + selfUser.getUserName();
         }
         
         commonScope = new SeppelScope();
         seppelSpace.put(scopeName, commonScope);
         commonScope.withScopeName(scopeName);
         
         // add scope to server channel, the server will add the other user
         SeppelSpaceProxyPO spaceProxyPO = selfProxy.getPartners().hasSeppelSpaceProxyPO();
         SeppelScopePO scopePO = spaceProxyPO.hasScopes();
         ChatUserPO chatUser = scopePO.hasObservedObjects().instanceOf(new ChatUserPO());
         chatUser.getPattern().getJsonIdMap().withCreator(ChatUserCreator.createIdMap("m42"));
         chatUser.hasUserName(currentUser.getUserName());
         
         if (spaceProxyPO.getHasMatch())
         {
            spaceProxyPO.getCurrentMatch().withScopes(commonScope);
         }
         
         selfProxy.withScopes(commonScope);
         
         commonScope.withObservedObjects(selfUser, currentUser);
         
         currentChannel = new ChatChannel();
         commonScope.withObservedObjects(currentChannel);
         
         selfUser.withChannels(currentChannel);
         currentUser.withChannels(currentChannel);
      }

      if ( ! observedChannels.contains(currentChannel))
      {
         commonScope = selfProxy.getScopes().hasObservedObjects(currentChannel).first();
         currentChannel.getPropertyChangeSupport().addPropertyChangeListener(ChatChannel.PROPERTY_MSGS, new ChatChannelScopeUpdater(commonScope));
         observedChannels.add(currentChannel);
      }
      
      
      String chatContent = currentChannel.getMsgs().getText().concat("\n");
      chatText.setText(chatContent);
      
      chatTextUpdater.setCurrentChannel(currentChannel);
      
   }
   
   private void addReplicationChatTaskHandler()
   {
      seppelSpace.withTaskHandler(
         new SeppelTaskHandler()
         .with("tomChatWithSabine", new SeppelBoardTaskAction() 
         {
            @Override
            public void run(BoardTask task)
            {
               // do a screen dump of the current gui
               WritableImage snapshot = stage.getScene().snapshot(null);
               
               File file = new File("doc/SeppelChatTomGUI1.png");

               try {
                   ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
               } catch (Exception s) {
               }
               
               // add to story
               
               Storyboard story = (Storyboard) task.getFromTaskObjects("story");
               story.addImage("SeppelChatTomGUI1.png");
               final SeppelScope cmdScope = selfProxy.getScopes().hasScopeName("commands").first();
               cmdScope.withObservedObjects(story.getStoryboardSteps().last());
               
               // now select sabine for chatting
               ToggleGroup toggleGroup = channelListUpdater.getToggleGroup();
               for (Toggle toggle : toggleGroup.getToggles())
               {
                  ChatUser user = (ChatUser) toggle.getUserData();
                  
                  if ("sabine".equals(user.getUserName()))
                  {
                     toggle.setSelected(true);
                     break;
                  }
               }
               
               
               Platform.runLater(new Runnable()
               {
                  
                  @Override
                  public void run()
                  {
                     // enter greeting
                     chatInput.setText("Hello Sabine, greetings from Tom");
                     
                     Platform.runLater(new Runnable()
                     {
                        
                        @Override
                        public void run()
                        {
                           // click send
                           chatButton.fire();

                           // ask the server to ask sabine to check the inbox
                           SeppelSpaceProxy zuenFamProxy = selfProxy.getPartners().hasLoginName("zuenFamilyChatServer").first();
                           BoardTask boardTask = cmdScope.add(new BoardTask().withName("sabineCheckInbox"));

                           zuenFamProxy.withTasks(boardTask);
                        }
                     });
                  }
               });
            }
         })
         .with("sabineCheckInbox", new SeppelBoardTaskAction() 
         {
            @Override
            public void run(BoardTask task)
            {

               // ask the server to terminate the test
               SeppelSpaceProxy serverProxy = selfProxy.getPartners().hasLoginName("zuenFamilyChatServer").first();
               SeppelScope cmdScope = selfProxy.getScopes().hasScopeName("commands").first();

               BoardTask boardTask = cmdScope.add(new BoardTask().withName("terminate"));

               serverProxy.withTasks(boardTask);
            }
         }));

   }



}
