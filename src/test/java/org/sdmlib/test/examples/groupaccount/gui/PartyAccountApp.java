package org.sdmlib.test.examples.groupaccount.gui;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import org.sdmlib.models.YamlFileMap;
import org.sdmlib.test.examples.groupaccount.model.Party;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartyAccountApp extends Application
{
   private Party party;
   private ScheduledExecutorService executor;
   private String userName;
   public VBox root;


   public PartyAccountApp withUserName(String userName)
   {
      this.userName = userName;
      return this;
   }

   public String getUserName()
   {
      return userName;
   }



   public Party getParty()
   {
      return party;
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception
   {
      root = getRoot();
   
      Scene scene = new Scene(root, 600, 700);
      
      stage.setScene(scene);
      stage.setTitle(userName);
      stage.setX(1300);
      stage.show();

      stage.setOnCloseRequest(e -> System.exit(0));

   }


   public VBox getRoot()
   {
      Parameters parameters = getParameters();
      
      if ( parameters != null)
      {
         List<String> params = parameters.getRaw();
         
         if ( ! params.isEmpty())
         {
            userName = params.get(0);
         }
      }
      
      VBox root = new VBox(18);
      root.setPadding(new Insets(27));
      root.setStyle("-fx-background-color: white;");
      
      root.setPrefSize(600, 700);
      
      // model
      party = new Party();

      new PartyControl().init(root, party);

      new YamlFileMap(userName,"aStore/TermStart", party);

      return root;
   }

}
