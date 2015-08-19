package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ModelSpaceProxyListener implements PropertyChangeListener
{
   private LinkedHashSet<ModelSpaceProxy> observedModelSpaceProxies = new LinkedHashSet<ModelSpaceProxy>();
   private ModelCloud modelCloud;
   private VBox modelSpaceProxiesVbox;
   
   public ModelSpaceProxyListener(ModelCloud modelCloud, VBox modelSpaceProxiesVbox)
   {
      this.modelCloud = modelCloud;
      this.modelSpaceProxiesVbox = modelSpaceProxiesVbox;
      
      this.propertyChange(null); 
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      for (ModelSpaceProxy proxy : modelCloud.getModelSpaces())
      {
         if ( ! observedModelSpaceProxies.contains(proxy))
         {
            observedModelSpaceProxies.add(proxy);

            Label label = new Label(proxy.getLocation());
            modelSpaceProxiesVbox.getChildren().add(label);
         }
      }
   }
}
