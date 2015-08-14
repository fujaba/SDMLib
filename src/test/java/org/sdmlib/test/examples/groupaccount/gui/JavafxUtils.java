package org.sdmlib.test.examples.groupaccount.gui;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;


public class JavafxUtils
{
   public void bindString(TextField textField, Object beanObject, String propertyName)
   {
      try
      {
         JavaBeanStringProperty nameProperty = JavaBeanStringPropertyBuilder.create().bean(beanObject).name(propertyName).build();
         textField.textProperty().bindBidirectional(nameProperty);
      }
      catch (NoSuchMethodException e)
      {
         e.printStackTrace();
      }
   }

   public void bindDouble(TextField textField, Object beanObject, String propertyName)
   {
      try
      {
         Property<Number> nameProperty = JavaBeanDoublePropertyBuilder.create().bean(beanObject).name(propertyName).build();
         Bindings.bindBidirectional(textField.textProperty(), nameProperty, new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
            {
               String text = String.format(Locale.ENGLISH, "%.2f €", obj);
               StringBuffer buf = new StringBuffer(text);
               return buf;
            }

            @Override
            public Object parseObject(String source, ParsePosition pos)
            {
               int europos = source.indexOf("€");
               if (europos >= 0)
               {
                  source = source.substring(0, europos);
               }
               double value = Double.parseDouble(source);
               pos.setIndex(1);
               return value;
            }
            
         });
      }
      catch (NoSuchMethodException e)
      {
         e.printStackTrace();
      }
   }

   public void bindDouble(Label textField, Object beanObject, String propertyName)
   {
      try
      {
         Property<Number> nameProperty = JavaBeanDoublePropertyBuilder.create().bean(beanObject).name(propertyName).build();
         Bindings.bindBidirectional(textField.textProperty(), nameProperty, new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
            {
               String text = String.format(Locale.ENGLISH, "%.2f €", obj);
               StringBuffer buf = new StringBuffer(text);
               return buf;
            }

            @Override
            public Object parseObject(String source, ParsePosition pos)
            {
               int europos = source.indexOf("€");
               if (europos >= 0)
               {
                  source = source.substring(0, europos);
               }
               double value = Double.parseDouble(source);
               pos.setIndex(1);
               return value;
            }
            
         });
      }
      catch (NoSuchMethodException e)
      {
         e.printStackTrace();
      }
   }

   public TextField createTextField(Pane parent)
   {
      TextField textField = new TextField();
      textField.setStyle("-fx-background-color: -fx-control-inner-background, -fx-control-inner-background, -fx-control-inner-background;");
      parent.getChildren().add(textField);
      
      return textField;
   }
   
   public Label createLabel(Pane parent)
   {
      Label label;
      label = new Label();
      label.setPadding(new Insets(4, 8, 4, 8));
      parent.getChildren().add(label);
      
      return label;
   }


   public Label createLabel(Pane parent, String text)
   {
      Label label;
      label = new Label(text);
      label.setPadding(new Insets(4, 8, 4, 8));
      parent.getChildren().add(label);
      
      return label;
   }


   public Label createLabel(HBox parent, String text, int width)
   {
      Label label = createLabel(parent, text);
      label.setPrefWidth(width);
      label.setAlignment(Pos.CENTER_RIGHT);
      return label;
   }

   public Label createEuroLabel(HBox parent)
   {
      int width = 68;
      
      Label label = new Label();
      label.setPadding(new Insets(4, 3, 4, 8));
      parent.getChildren().add(label);
      label.setPrefWidth(width);
      label.setAlignment(Pos.CENTER_RIGHT);
      
      Label euroLabel = new Label("€");
      euroLabel.setPadding(new Insets(4, 0, 4, 0));
      parent.getChildren().add(euroLabel);
      
      return label;
   }

   
   
   
   public void addVisibilityToggle(Pane participantsListPlusButton, Button button, Button... moreButtons)
   {
      participantsListPlusButton.setOnMouseEntered(new EventHandler<Event>()
      {
         @Override
         public void handle(Event event)
         {
            button.setVisible(true);
         }
      });
   
      participantsListPlusButton.setOnMouseExited(new EventHandler<Event>()
      {
         @Override
         public void handle(Event event)
         {
            button.setVisible(false);
         }
      });
      
      button.focusedProperty().addListener(new ChangeListener<Boolean>()
         {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
               for (Button other : moreButtons)
               {
                  other.setVisible(newValue);
               }
               button.setVisible(newValue);
            }
         });
   }

   public Button createButtonInBox(String name, HBox hBox, int i)
   {
      HBox buttonBox = new HBox();
      hBox.getChildren().add(buttonBox);
      buttonBox.setPadding(new Insets(0,0,0,i));
      
      Button button = new Button(name);
      buttonBox.getChildren().add(button);
      button.setVisible(false);
      
      return button;
   }

}
