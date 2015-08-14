package org.sdmlib.test.examples.modelspace.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChannelDescriptionController implements PropertyChangeListener
{

   private MSChatChannelDescription modelObject;
   private HBox hBox;
   private Label nameLabel;
   private Label locationLabel;
   private MSChatGroupClient app;

   public HBox init(Application theApp, Object modelObject)
   {
      this.app = (MSChatGroupClient) theApp;
      this.modelObject = (MSChatChannelDescription) modelObject;
      
      // Label for the name
      nameLabel = new Label();
      nameLabel.setPrefWidth(70);
      
      locationLabel = new Label();
      
      hBox = new HBox (nameLabel, locationLabel);
      hBox.setSpacing(10);
      
      this.modelObject.getPropertyChangeSupport().addPropertyChangeListener(this);
      
      this.propertyChange(null);
      
      hBox.setOnMouseClicked(new EventHandler<Event>()
      {

         @Override
         public void handle(Event arg0)
         {
            // open channel
            app.openChannel(locationLabel.getText());
         }
      });
      
      return hBox;
      
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      nameLabel.setText(this.modelObject.getName());
      locationLabel.setText(this.modelObject.getLocation());
   }

}
