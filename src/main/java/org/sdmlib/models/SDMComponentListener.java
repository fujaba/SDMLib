package org.sdmlib.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;
import java.util.Set;

import de.uniks.networkparser.interfaces.SendableEntity;

public class SDMComponentListener implements PropertyChangeListener
{

   private PropertyChangeListener elementListener;
   
   private Set<SendableEntity> componentElements = new LinkedHashSet<SendableEntity>();

   public SDMComponentListener(SendableEntity root, PropertyChangeListener elementListener)
   {
      this.elementListener = elementListener;
      
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // TODO Auto-generated method stub

   }

}
