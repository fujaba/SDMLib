package org.sdmlib.modelspace;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import org.sdmlib.javafx.ModelObjectController;
import org.sdmlib.serialization.PropertyChangeInterface;

public class ModelCloudProxyController extends ModelObjectController
{
   private HBox hBox;
   private ModelCloudProxy proxy;

   @Override
   public Node init(PropertyChangeInterface listElem)
   {
      this.proxy = (ModelCloudProxy) listElem;
      
      hBox = new HBox(8);
      
      Label label1 = new Label(proxy.getHostName());
      label1.setPrefWidth(100);
      
      Label label2 = new Label(""+proxy.getPortNo());
      
      hBox.getChildren().addAll(label1, label2);
      
      return hBox;
   }

   @Override
   public Node getNode()
   {
      // TODO Auto-generated method stub
      return hBox;
   }
}
