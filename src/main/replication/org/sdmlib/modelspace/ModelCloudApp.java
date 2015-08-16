package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.sdmlib.javafx.FX;
import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.modelspace.util.ModelCloudCreator;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatGroupCreator;

import de.uniks.networkparser.json.JsonIdMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ModelCloudApp extends Application
{
   public static void main(String... args)
   {
      launch(args);
   }

   private String location;
   private VBox root;
   private String userName;
   private String sessionId;
   private JsonIdMap idMap;
   private ModelCloud modelCloud;
   private ModelSpace space;
   private TextField acceptPortField;
   private HBox addServerBox;
   private TextField hostNameField;
   private TextField portNoField;
   
   @Override
   public void start(Stage stage) throws Exception
   {
      if (location == null)
      {
         Parameters params = this.getParameters();
         List<String> parameters = params.getRaw();
         
         if (parameters.size() > 0)
         {
            location = parameters.get(0);
         }
         else
         {
            location = "modeldata";
         }
      }

      userName = "cloudServer";
      
      sessionId = userName + System.currentTimeMillis();

      idMap = ModelCloudCreator.createIdMap(sessionId);

      modelCloud = new ModelCloud();

      idMap.put(location, modelCloud);

      space = new ModelSpace(idMap, userName, ApplicationType.JavaFX).open(location + "/.cloudData");

      
      Label label = new Label("accept port:");
      
      acceptPortField = new TextField();
      FX.bindTextFieldIntegerProperty(idMap, acceptPortField, modelCloud, ModelCloud.PROPERTY_ACCEPTPORT);
      
      
      HBox hBox = new HBox(8);
      hBox.getChildren().addAll(label, acceptPortField);
      
      Label otherServersLabel = new Label ("Other cloud servers:");
      
      VBox otherSeversVBox = new VBox(8);
      FX.bindListProperty(idMap, otherSeversVBox, modelCloud, ModelCloud.PROPERTY_SERVERS, ModelCloudProxyController.class);

      hostNameField = new TextField("hostName?");
      portNoField = new TextField("portNo?");
      Button button = new Button("Add");
      button.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent arg0)
         {
            // create ModelCloudProxy and add to servers
            ModelCloudProxy modelCloudProxy = new ModelCloudProxy()
               .withHostName(hostNameField.getText());
            
            int portNo = Integer.parseInt(portNoField.getText());
            modelCloudProxy.setPortNo(portNo);
            modelCloud.withServers(modelCloudProxy);
         }
      });
      
      addServerBox = new HBox(8);
      addServerBox.getChildren().addAll(hostNameField, portNoField, button);
      
      root = new VBox(8);
      root.setPadding(new Insets(24));
      root.getChildren().addAll(hBox, otherServersLabel, otherSeversVBox, addServerBox);
      
      ScrollPane scrollPane = new ScrollPane(root);
      
      scrollPane.setStyle("-fx-background: white;");
      
      Scene scene = new Scene(scrollPane, 400, 600);
      
      stage.setScene(scene);
      
      stage.setTitle("Model Cloud " + location);
      
      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
         {

         @Override
         public void handle(WindowEvent arg0)
         {
            Platform.exit();
            System.exit(0);
         }
         });

      stage.show();
      
      // open server socket
      PropertyChangeListener listener = new PropertyChangeListener()
      {
         @Override
         public void propertyChange(PropertyChangeEvent evt)
         {
            if (modelCloud.getAcceptPort() > 0)
            {
               ServerSocketHandler serverSocketHandler = new ServerSocketHandler(modelCloud);
               serverSocketHandler.start();
            }
         }
      };
      
      modelCloud.getPropertyChangeSupport().addPropertyChangeListener(ModelCloud.PROPERTY_ACCEPTPORT, listener);
      
      listener.propertyChange(null);
      
      // connect to other servers
      modelCloud.getPropertyChangeSupport().addPropertyChangeListener(ModelCloud.PROPERTY_SERVERS, new OtherServersListener(modelCloud));
   }

}
