package org.sdmlib.test.examples.groupaccount.gui;

import java.text.NumberFormat;
import java.text.ParseException;

import javafx.beans.Observable;
import org.sdmlib.test.examples.groupaccount.model.Item;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemControl
{

   private Item item;

   public void init(Item newItem, VBox itemListVBox)
   {
      this.item = newItem;
      
      HBox hBox = new HBox(9);
      
      hBox.setAlignment(Pos.CENTER_LEFT);
      
      TextField descriptionField = new TextField();
      descriptionField.textProperty().addListener(e -> descriptionFieldListener(descriptionField));
      item.addPropertyChangeListener(Item.PROPERTY_DESCRIPTION, e -> descriptionAttrListener(descriptionField));
      
      descriptionField.setPrefWidth(300);
      
      TextField priceField = new TextField("0");
      
      priceField.setAlignment(Pos.CENTER_RIGHT);
      
      priceField.textProperty().addListener(e -> priceFieldListener(priceField));

      priceField.focusedProperty().addListener(e -> priceFieldFocusListener(item, priceField));
      
      item.addPropertyChangeListener(Item.PROPERTY_PRICE, e -> priceAttrListener(priceField));
      
      Label label = new Label("€");
      
      hBox.getChildren().addAll(descriptionField, priceField, label);
      
      itemListVBox.getChildren().add(hBox);
      
      descriptionField.requestFocus();
   }

   private void priceFieldFocusListener(Item item, TextField field)
   {
      if ( ! field.isFocused())
      {
         String string = String.format("%.2f", item.getPrice());
         field.setText(string);
      }
   }

   private void descriptionAttrListener(TextField descriptionField)
   {
      descriptionField.setText(item.getDescription());
   }

   private void descriptionFieldListener(TextField descriptionField)
   {
      item.setDescription(descriptionField.getText());
   }

   private void priceAttrListener(TextField priceField)
   {
      double newPrice = item.getPrice();

      NumberFormat format = NumberFormat.getInstance();

      String string = format.format(newPrice);

      priceField.setText(string);
      
      return;
   }

   private void priceFieldListener(TextField priceField)
   {
      String text = priceField.getText();
      
      NumberFormat formatter = NumberFormat.getInstance();
      
      try
      {
         Number number = formatter.parse(text);
         
         double newPrice = number.doubleValue();

         item.setPrice(newPrice);
         
         priceField.setStyle("-fx-border-color:green");
         
      }
      catch (ParseException e)
      {
         // change border color
         priceField.setStyle("-fx-border-color:red");
      }
      
      
      
      return;
   }

}
