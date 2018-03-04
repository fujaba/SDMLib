package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;

import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PartyControl
{

   private VBox root;
   private Party party;
   private TextField partyNameField;
   private VBox personsVBox;

   @SuppressWarnings("restriction")
   public void init(VBox root, Party party)
   {
      this.root = root;
      this.party = party;
      
      partyNameField = new TextField("");
      partyNameField.setId("pnf");
      partyNameField.setPromptText("Party name?");
      // partyNameField.setStyle("-fx-border-color: white; -fx-background-color: white;");
      
      
      partyNameField.textProperty().addListener(e -> nameFieldChange());
      party.addPropertyChangeListener(Party.PROPERTY_PARTYNAME, e -> logicNameChange());
      
      // total and share
      Label pre = new Label(" ");
      pre.setPrefWidth(120);
      Label totalLabel = new Label("Total:");
      Label totalField = new Label("0,00");
      Label euroLabel = new Label("€");
      euroLabel.setPrefWidth(20);
      totalField.setStyle("-fx-background-color: white;");
      totalField.setPrefWidth(110);
      totalField.setAlignment(Pos.CENTER_RIGHT);
      party.addPropertyChangeListener(Party.PROPERTY_TOTAL, e -> updateTotalField(totalField));
      
      Label shareLabel = new Label("Share:");
      Label shareField = new Label("0,00");
      Label euro2 = new Label("€");

      shareField.setStyle("-fx-background-color: white;");
      shareField.setPrefWidth(100);
      shareField.setAlignment(Pos.CENTER_RIGHT);
      party.addPropertyChangeListener(Party.PROPERTY_SHARE, e -> updateShareField(shareField));
      
      HBox totalBox = new HBox(9);
      totalBox.getChildren().addAll(pre, totalLabel, totalField, euroLabel, shareLabel, shareField, euro2);
      totalBox.setAlignment(Pos.CENTER_LEFT);
      
      personsVBox = new VBox(36);
      
      Button addPersonButton = new Button("add Person");
      addPersonButton.setId("addPersonButton");
      addPersonButton.setOnAction(e -> addPersonAction());
      
      party.addPropertyChangeListener(Party.PROPERTY_GUESTS, e -> newPersonListener(e));
    
      root.getChildren().addAll(partyNameField, totalBox, personsVBox, addPersonButton);
      
   }

   private void updateShareField(Label shareField)
   {
      String string = String.format("%.2f", party.getShare());
      
      shareField.setText(string);
      
   }

   private void updateTotalField(Label totalField)
   {
      String string = String.format("%.2f", party.getTotal());
      
      totalField.setText(string);
   }

   private void newPersonListener(PropertyChangeEvent evt)
   {
      Person person = (Person) evt.getNewValue();
      
      new PersonControl().init(person, personsVBox);
      
      updatePartyTotalAndShare();
      
      person.addPropertyChangeListener(Person.PROPERTY_TOTAL, e -> updatePartyTotalAndShare());
   }

   private void updatePartyTotalAndShare()
   {
      double sum = party.getGuests().getTotal().sum();
      
      updateShare(sum);
      party.setTotal(sum);
   }

   private void updateShare(double sum)
   {
      if ( ! party.getGuests().isEmpty())
      {
         double share = sum / party.getGuests().size();
         party.setShare(share);
      }
   }

   private void addPersonAction()
   {
      Person person = party.createGuests();
   }

   private Object logicNameChange()
   {
      partyNameField.setText(party.getPartyName());
      
      return null;
   }

   private void nameFieldChange()
   {
      String text = partyNameField.getText();
      party.setPartyName(text);
      // Logger.getGlobal().info("got " + text + " from name field, assigned it to name of model party");
   }

}
