package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.javafx.ModelObjectController;
import org.sdmlib.serialization.PropertyChangeInterface;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ModelCloudProxyController extends ModelObjectController implements PropertyChangeListener
{
   private HBox hBox;
   private ModelCloudProxy proxy;
   private Label stateLabel;

   @Override
   public Node init(PropertyChangeInterface listElem)
   {
      this.proxy = (ModelCloudProxy) listElem;
      
      hBox = new HBox(8);
      
      Label label1 = new Label(proxy.getHostName());
      label1.setPrefWidth(150);
      
      Label label2 = new Label(""+proxy.getPortNo());
      label2.setPrefWidth(130);
      
      stateLabel = new Label(""+proxy.getState());
      proxy.getPropertyChangeSupport().addPropertyChangeListener(ModelCloudProxy.PROPERTY_STATE, this);
      
      hBox.getChildren().addAll(label1, label2, stateLabel);
      
      return hBox;
   }

   @Override
   public Node getNode()
   {
      // TODO Auto-generated method stub
      return hBox;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      stateLabel.setText(proxy.getState());
   }
}
