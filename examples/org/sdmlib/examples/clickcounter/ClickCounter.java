package org.sdmlib.examples.clickcounter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

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
      grid.add(label, 1, 0);
      
      Label dataLabel = new Label();
      
      grid.add(dataLabel, 2, 0);
      
      JavaBeanIntegerProperty beanProperty = JavaBeanIntegerPropertyBuilder.create().bean(data).name(Data.PROPERTY_NUM).build();
     
      dataLabel.textProperty().bind(beanProperty.asString());
            
      Label fxdataLabel = new Label();
      
      fxdataLabel.setTextAlignment(TextAlignment.RIGHT);
      grid.add(fxdataLabel, 2, 1);
      
      fxdataLabel.textProperty().bind(data.getFxnum().asString());
      
      Button button = new Button("Clicke Me");
      grid.add(button, 2, 2);
      
      button.setOnMouseClicked(new EventHandler<Event>()
      {

         @Override
         public void handle(Event arg0)
         {
            data.setNum(data.getNum() + 1);
            
            data.getFxnum().set(data.getFxnum().get()+1);
            
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
