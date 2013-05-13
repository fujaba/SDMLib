package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LaneListener implements PropertyChangeListener
{

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // subscribe to interesting lanes
      System.out.println("lane event");

   }

}
