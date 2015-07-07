package org.sdmlib.test.examples.replication.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


public class ChannelListUpdater implements PropertyChangeListener, ChangeListener<Toggle>
{
   
   
   private ChatRoot chatRoot;
   private VBox vbox;
   private String userName;
   
   private ToggleGroup toggleGroup;
   
   public ToggleGroup getToggleGroup()
   {
      return toggleGroup;
   }
   
   private ReplicationChatClientApp app;

   public ChannelListUpdater(ReplicationChatClientApp replicationChatClientApp, ChatRoot chatRoot, VBox channelListVBox, String userName)
   {
      this.app = replicationChatClientApp;
      this.chatRoot = chatRoot;
      this.vbox = channelListVBox;
      this.toggleGroup = new ToggleGroup();
      this.userName = userName;

      chatRoot.getPropertyChangeSupport().addPropertyChangeListener(this);
      
      for (ChatUser user : chatRoot.getUsers())
      {
         if ( ! userName.equals(user.getUserName()))
         {
            RadioButton radioButton = new RadioButton(user.getUserName());
            radioButton.setUserData(user);
            radioButton.setToggleGroup(toggleGroup);
            vbox.getChildren().add(radioButton);
         }
      }
      
      this.toggleGroup.selectedToggleProperty().addListener(this);
      
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // if new users join, add them to the list of possible chat channels
      if (evt.getSource() == chatRoot 
            && evt.getPropertyName().equals(ChatRoot.PROPERTY_USERS) 
            && evt.getNewValue() != null)
      {
         ChatUser user = (ChatUser) evt.getNewValue();
         if ( ! userName.equals(user.getUserName()) && ! hasRadioButton(user))
         {
            RadioButton radioButton = new RadioButton(user.getUserName());
            radioButton.setUserData(user);
            radioButton.setToggleGroup(toggleGroup);
            vbox.getChildren().add(radioButton);
         }
      }
      
      
   }

   private boolean hasRadioButton(ChatUser user)
   {
      for (Toggle rb : toggleGroup.getToggles())
      {
         if (rb.getUserData() == user)
         {
            return true;
         }
      }
      return false;
   }

   @Override
   public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2)
   {
      if (this.toggleGroup.getSelectedToggle() != null)
      {
         ChatUser currentUser = (ChatUser) this.toggleGroup.getSelectedToggle().getUserData();
         app.chatWithCurrentUser(currentUser);
      }
      
   }

}
