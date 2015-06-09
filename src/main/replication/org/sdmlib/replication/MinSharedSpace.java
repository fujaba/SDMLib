package org.sdmlib.replication;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.LinkedBlockingQueue;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.UpdateListener;

public class MinSharedSpace extends Thread implements PropertyChangeInterface
{
   private LinkedBlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

   
   //==========================================================================
   @Override
   public void run()
   {
      while (true)
      {
         try
         {
            String msg = msgQueue.take();

            handleMessage(msg);
         }
         catch (Exception e)
         {
            // just try again
            e.printStackTrace();
         }
      }
   }



   //==========================================================================
   
   public void handleMessage(String msg)
   {
      // msg should be a change event, store it into history and inform everybody
      
   }



   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
}
