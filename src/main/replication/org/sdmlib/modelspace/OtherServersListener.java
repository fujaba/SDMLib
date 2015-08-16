package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;

public class OtherServersListener implements PropertyChangeListener
{
   private LinkedHashSet<ModelCloudProxy> observedServers = new LinkedHashSet<ModelCloudProxy>();
   private ModelCloud modelCloud;
   
   public OtherServersListener(ModelCloud modelCloud)
   {
      this.modelCloud = modelCloud;
      this.propertyChange(null); 
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt == null || evt.getSource() == modelCloud)
      {
         // new server? 
         for (ModelCloudProxy proxy : modelCloud.getServers())
         {
            if ( ! observedServers.contains(proxy))
            {
               observedServers.add(proxy);

               ModelCloudChannel listener = proxy.getChannel();
               
               if (listener == null)
               {
                   listener = new ModelCloudChannel(modelCloud, proxy);
                  
               }
               
               proxy.getPropertyChangeSupport().addPropertyChangeListener(listener);
            }
         }
      }
   }

}
