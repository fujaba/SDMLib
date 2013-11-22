package org.sdmlib.examples.clickcounter;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import com.sun.javafx.fxml.BeanAdapter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ClickCounter extends Application
{
   
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception
   {
      
      GridPane grid = new GridPane();
      
      Label label = new Label("Button has been clicked: ");
      grid.add(label, 0, 0);
      
      Label dataLabel = new Label();
      
      grid.add(dataLabel, 2, 0);
      
      JavaBeanIntegerProperty beanProperty = JavaBeanIntegerPropertyBuilder.create().bean(data).name(Data.PROPERTY_NUM).build();
     
      dataLabel.textProperty().bind(beanProperty.asString());
            
      Label fxdataLabel = new Label();
      
      grid.add(fxdataLabel, 3, 0);
      
      fxdataLabel.textProperty().bind(data.getFxnum().asString());
      
      Button button = new Button("Clicke Me");
      grid.add(button, 1, 1);
      
      button.setOnMouseClicked(new EventHandler<Event>()
      {

         @Override
         public void handle(Event arg0)
         {
            data.setNum(data.getNum() + 1);
            System.out.println("now: " + data.getNum());
         }
      });
      
      grid.setAlignment(Pos.CENTER);
      
      Scene scene = new Scene(grid, 400, 300);

      stage.setTitle("Click Counter");
      stage.setScene(scene);
      stage.show();

      CountDown countDown = new CountDown();
      countDown.start();
   }
   
   public class CountDown extends Thread
   {
      @Override
      public void run()
      {
         while (true)
         {
            synchronized (this)
            {

               try
               {
                  wait(1000);
               }
               catch (InterruptedException e)
               {
               }
            }
            
            Platform.runLater(new Runnable()
            {
               
               @Override
               public void run()
               {
                  data.setNum(data.getNum() - 1);
               }
            });
            
         }
      }
   }
   
   private Data data = new Data();
   
}
