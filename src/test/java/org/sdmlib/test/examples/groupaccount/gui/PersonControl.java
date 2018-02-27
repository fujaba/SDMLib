package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;

import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PersonControl
{

   private TextField personNameField;
   private Person person;
   private VBox itemListVBox;

   public void init(Person person, VBox personListVBox)
   {
      this.person = person;
      
      person.getParty().addPropertyChangeListener(Party.PROPERTY_SHARE, e -> updateSaldo());
      
      VBox onePersonBox = new VBox(18);
      
      personNameField = new TextField();
      personNameField.textProperty().addListener(e->nameFieldChange());
      person.addPropertyChangeListener(Person.PROPERTY_NAME, e -> logicNameChange());
      
      Label saldoLabel = new Label("Saldo:");
      Label saldoValue = new Label("0,00");
      saldoValue.setAlignment(Pos.CENTER_RIGHT);
      saldoValue.setStyle("-fx-background-color: white;");
      saldoValue.setPrefWidth(100);
      
      person.addPropertyChangeListener(Person.PROPERTY_SALDO, e -> updateSaldoLabel(saldoValue));
      
      HBox nameLine = new HBox(9);
      nameLine.setAlignment(Pos.CENTER_LEFT);
      nameLine.getChildren().addAll(personNameField, saldoLabel, saldoValue);
      
      itemListVBox = new VBox(18);
      
      Button addItemButton = new Button("add Item");
      addItemButton.setOnAction(e -> addItemAction());
      person.addPropertyChangeListener(Person.PROPERTY_ITEMS, e -> logicNewItemListener(e));
      
      onePersonBox.getChildren().addAll(nameLine, itemListVBox, addItemButton);
      
      personListVBox.getChildren().addAll(onePersonBox);
      
      personNameField.requestFocus();
   }

   private void updateSaldoLabel(Label saldoValue)
   {
      String string = String.format("%.2f", person.getSaldo());;
      
      saldoValue.setText(string);
   }

   private void updateSaldo()
   {
      double newSaldo = person.getParty().getShare() - person.getTotal();
      person.setSaldo(newSaldo);
   }

   private void logicNewItemListener(PropertyChangeEvent evt)
   {
      Item newItem = (Item) evt.getNewValue();
      new ItemControl().init(newItem, itemListVBox);
      
      newItem.addPropertyChangeListener(Item.PROPERTY_PRICE, e -> updatePersonTotal());
   }

   private void updatePersonTotal()
   {
      double sum = person.getItems().getPrice().sum();
      
      person.setTotal(sum);
   }

   private void addItemAction()
   {
      Item newItem = person.createItems();
   }

   private void logicNameChange()
   {
      String newName = person.getName();
      personNameField.setText(newName);
   }

   private void nameFieldChange()
   {
      person.setName(personNameField.getText());
   }

}
