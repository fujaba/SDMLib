package org.sdmlib.models.classes;

import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.util.PropertyChangeInterface;

public abstract class SDMLibClass implements PropertyChangeInterface
{  
   public static final String PROPERTY_NAME = "name";

   protected String name = null;
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
  
   public String getName()
   {
      return name;
   }
   
   public abstract SDMLibClass withName(String value);
}
