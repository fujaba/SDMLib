package org.sdmlib.javafx;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;

public class FX
{

   public static void bindTextFieldIntegerProperty(IdMap idMap, final TextField field, 
         final PropertyChangeInterface modelObject, final String property)
   {
      final SendableEntityCreator creator = idMap.getCreatorClass(modelObject);
      
      // bind property change listener to model object
      PropertyChangeListener listener = new PropertyChangeListener()
      {  
         @Override
         public void propertyChange(PropertyChangeEvent evt)
         {
            int value = (int) creator.getValue(modelObject, property);
            field.setText("" + value);
         }
      };
      
      modelObject.getPropertyChangeSupport().addPropertyChangeListener(property, listener);
      
      listener.propertyChange(null);
      
      // add listener to text field
      field.setOnAction(new EventHandler<ActionEvent>()
      {
         
         @Override
         public void handle(ActionEvent arg0)
         {
            int value = Integer.parseInt(field.getText());
            creator.setValue(modelObject, property, value, null);
         }
      });
      
      field.focusedProperty().addListener(new ChangeListener<Boolean>()
      {

         @Override
         public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean newValue)
         {
            if (newValue == false)
            {
               int value = Integer.parseInt(field.getText());
               creator.setValue(modelObject, property, value, null);
            }
         }
      });
      
   }

   public static void bindListProperty(IdMap idMap, VBox vBox, PropertyChangeInterface listRoot,
         String property, Class<? extends ModelObjectController> elementControllerClass)
   {
      new ModelListController(idMap, vBox, listRoot, property, elementControllerClass);
   }

}
