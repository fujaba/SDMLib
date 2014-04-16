package org.sdmlib.models.classes;

import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.util.PropertyChangeInterface;

public class SDMLibClass implements PropertyChangeInterface
{

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
}
