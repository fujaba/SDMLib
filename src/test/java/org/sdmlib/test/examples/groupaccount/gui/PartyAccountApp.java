package org.sdmlib.test.examples.groupaccount.gui;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.sdmlib.models.YamlIdMap;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.util.PartyCreator;

import de.uniks.networkparser.ext.petaf.NodeProxy;
import de.uniks.networkparser.ext.petaf.Space;
import javafx.application.Application;
import javafx.application.Platform;
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

//      executor = Executors.newSingleThreadScheduledExecutor();
//      
//      executor.schedule(()-> runLaterAutoStore(), 5, TimeUnit.SECONDS);
   }

   private void runLaterAutoStore()
   {
      Platform.runLater(()->autoStore());
   }

   private void autoStore()
   {
      try
      {
         // save
         YamlIdMap idMap = new YamlIdMap("uks.pmwt1718.ha7.model");

         String text = idMap.encode(party);
         
         Files.write(Paths.get("aStore/party.yaml"), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally {
         // schedule again
         executor.schedule(()-> runLaterAutoStore(), 5, TimeUnit.SECONDS);
      }
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
      
      //  new SDMComponentListener(party, e -> myChangeLogger(e));

      new PartyControl().init(root, party);

      Space space = new Space();
      space.withCreator(PartyCreator.createIdMap("s"));
      space.createModel(party);
      space.createServer(42000);
      NodeProxy server = space.connectToPeer("localhost", 42000);     
      
      
      //      try
      //      {
      //         byte[] readAllBytes = Files.readAllBytes(Paths.get("aStore/party.yaml"));
      //
      //         String text = new String(readAllBytes);
      //
      //         YamlIdMap idMap = new YamlIdMap("uks.pmwt1718.ha7.model");
      //
      //         Object obj = idMap.decode(text, party);
      //      }
      //      catch (IOException e)
      //      {
      //         // TODO Auto-generated catch block
      //         e.printStackTrace();
      //      }
      //      

      return root;
   }
   
}
