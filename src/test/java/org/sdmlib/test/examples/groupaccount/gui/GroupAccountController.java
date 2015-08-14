package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;

import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Person;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GroupAccountController implements PropertyChangeListener
{
   private GroupAccount dataRoot;
   
   public GroupAccount getDataRoot()
   {
      return dataRoot;
   }
   
   private Group guiRoot;
   
   public Group getGuiRoot()
   {
      return guiRoot;
   }

   private LinkedHashMap<Person, PersonController> personControllers = new LinkedHashMap<>();
   
   public LinkedHashMap<Person, PersonController> getPersonControllers()
   {
      return personControllers;
   }
   
   private VBox rootVBox;
   
   public VBox getRootVBox()
   {
      return rootVBox;
   }
   
   private JavafxUtils javafxUtils = new JavafxUtils();
   private VBox participantsList;
   private Button addPersonButton;
   
   public GroupAccountController(GroupAccount dataRoot, Group guiRoot)
   {
      this.dataRoot = dataRoot;
      this.guiRoot = guiRoot;
      
      dataRoot.getPropertyChangeSupport().addPropertyChangeListener(this);
      dataRoot.getPropertyChangeSupport().firePropertyChange(null, null, null);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (guiRoot.getChildren().size() == 0)
      {
         rootVBox = new VBox(8);
         rootVBox.setPadding(new Insets(36));
         guiRoot.getChildren().add(rootVBox);
         
         javafxUtils.createLabel(rootVBox, "List of participants and their items:");
         
         participantsList = new VBox();
         rootVBox.getChildren().add(participantsList);
         participantsList.setPadding(new Insets(0, 0, 0, 12));
         
         
         HBox hBox = new HBox();
         rootVBox.getChildren().add(hBox);
         
         javafxUtils.createLabel(hBox, "Overview:");
         
         addPersonButton = javafxUtils.createButtonInBox("Add Person", hBox, 200 + 36);
         
         javafxUtils.addVisibilityToggle(rootVBox, addPersonButton);
         
         addPersonButton.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event)
            {
               dataRoot.createPersons().createItem();
            }
         });
         
         
         VBox vBox = new VBox();
         vBox.setPadding(new Insets(0, 0, 0, 28));
         rootVBox.getChildren().add(vBox);
         
         hBox = new HBox();
         vBox.getChildren().add(hBox);
         
         Label label = javafxUtils.createLabel(hBox, "Total purchase:", 200);
         
         label = javafxUtils.createEuroLabel(hBox);
         new LabelDouble0_00Binding(label, dataRoot, GroupAccount.PROPERTY_TOTALPURCHASE, dataRoot.getTotalPurchase());

         
      
         hBox = new HBox();
         vBox.getChildren().add(hBox);
         
         label = javafxUtils.createLabel(hBox, "Average purchase:", 200);
         
         label = javafxUtils.createEuroLabel(hBox);
         new LabelDouble0_00Binding(label, dataRoot, GroupAccount.PROPERTY_AVERAGEPURCHASE, dataRoot.getAveragePurchase());
      }

      
      // create controllers for new persons
      for (Person person : this.dataRoot.getPersons())
      {
         PersonController personController = personControllers.get(person);
         
         if (personController == null) 
         {         
            personController = new PersonController(this, person, participantsList, addPersonButton);
            personControllers.put(person, personController);
         }
      }
   }

}
