package org.sdmlib.test.examples.groupaccount.gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Locale;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;


public class PersonController implements PropertyChangeListener
{

   private final class NameLabeClickHandler implements EventHandler<Event>
   {
      @Override
      public void handle(Event arg0)
      {
         // open a text field
      }
   }

   private GroupAccountController groupAccountController;
   private Person person;
   public Person getPerson()
   {
      return person;
   }
   private VBox view;
   private TextField nameField;
   public TextField getNameField()
   {
      return nameField;
   }
   
   private LinkedHashMap<Item, ItemController> itemControllers = new LinkedHashMap<Item, ItemController>();
   
   public LinkedHashMap<Item, ItemController> getItemControllers()
   {
      return itemControllers;
   }
   
   private VBox parent;
   private Button addPersonButton;
   
   public VBox getView()
   {
      return view;
   }

   public PersonController(GroupAccountController groupAccountController, Person person, VBox parent, Button addPersonButton)
   {
      this.groupAccountController = groupAccountController;
      this.person = person;
      this.parent = parent;
      this.addPersonButton = addPersonButton;  
      
      person.getPropertyChangeSupport().addPropertyChangeListener(this);
      person.getPropertyChangeSupport().firePropertyChange(null, null, null);   
   }

   private JavafxUtils javafxUtils = new JavafxUtils();
   private VBox content;
   private VBox itemList;
   private Button addItemButton;
   private Label balanceLabel;
   
   public VBox getContent()
   {
      return content;
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      boolean doCreate = view == null;
      
      if (doCreate)
      {
         view = new VBox();
         parent.getChildren().add(view);
         

         nameField = javafxUtils.createTextField(view);
         nameField.setText(person.getName());
         nameField.textProperty().addListener(new ChangeListener<String>()
         {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2)
            {
               person.setName(nameField.getText());
            }
         });
         
         person.getPropertyChangeSupport().addPropertyChangeListener(Person.PROPERTY_NAME, new PropertyChangeListener()
         {
            
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
               nameField.setText(person.getName());
            }
         });
         
         // javafxUtils.bindString(nameField, person, Person.PROPERTY_NAME);
       
         content = new VBox();
         content.setPadding(new Insets(0, 0, 0, 16));
         view.getChildren().add(content);
         
         HBox itemListPlusButton = new HBox();
         itemListPlusButton.setAlignment(Pos.BOTTOM_LEFT);
         content.getChildren().add(itemListPlusButton);
         
         itemList = new VBox();
         itemListPlusButton.getChildren().add(itemList);
         
         HBox hBox = new HBox();
         
         Label label = javafxUtils.createLabel(hBox, "Total purchase:", 200);

         label = javafxUtils.createLabel(hBox, "0,00 €", 68);

         javafxUtils.bindDouble(label, person, Person.PROPERTY_TOTALPURCHASE);
         
         addItemButton = javafxUtils.createButtonInBox("Add Item", hBox, 8);
         javafxUtils.addVisibilityToggle(content, addItemButton, addPersonButton);

         
         addItemButton.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event)
            {
               person.createItem();
            }
         });
         
         
         content.getChildren().add(hBox);
         
         hBox = new HBox();
         content.getChildren().add(hBox);
         
         label = javafxUtils.createLabel(hBox, "Account balance:", 200);

         balanceLabel = javafxUtils.createLabel(hBox, "0,00 €", 68);

         PropertyChangeListener listener = new PropertyChangeListener()
         {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
               String text = String.format(Locale.ENGLISH, "%.2f €", person.getBalance());
               balanceLabel.setText(text);  
            }
         };
         
         listener.propertyChange(null);
         
         person.getPropertyChangeSupport().addPropertyChangeListener(Person.PROPERTY_BALANCE, listener);
         // javafxUtils.bindDouble(label, person, Person.PROPERTY_BALANCE);
         
         Button button = javafxUtils.createButtonInBox("Del Person", hBox, 8);

         javafxUtils.addVisibilityToggle(view, button, addPersonButton);
         button.setOnAction(new EventHandler<ActionEvent>()
            {
               @Override
               public void handle(ActionEvent event)
               {
                  if (person.getParent().getPersons().size() <= 1) return;
                  
                  // remove from model
                  person.removeYou();
                  // remove from view
                  parent.getChildren().remove(view);
               }
            });
         
         
         javafxUtils.createLabel(content, " ");
         
      }
      
      // update name field
      
      for (Item item : person.getItem())
      {
         ItemController itemControl = itemControllers.get(item);
         
         if (itemControl == null)
         {
            itemControl = new ItemController(this, item, itemList, addItemButton);
            itemControllers.put(item, itemControl);
         }
      }
      
      if (doCreate)
      {
         Platform.runLater(new Runnable()
         {
         
            @Override
            public void run()
            {
               nameField.requestFocus();
               nameField.selectAll();
            }
            
         });
      }
   }

}
