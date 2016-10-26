/*
   Copyright (c) 2016 Stefan
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.simple.model.abstract_C;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.abstract_C.Grass;
import org.sdmlib.simple.model.abstract_C.util.GroundSet;
import org.sdmlib.simple.model.abstract_C.Ground;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAbstractClazz.java'>TestAbstractClazz.java</a>
 */
   public  class Game implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutGrounds(this.getGrounds().toArray(new Ground[this.getGrounds().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- Ground
    *              game                   grounds
    * </pre>
    */
   
   public static final String PROPERTY_GROUNDS = "grounds";

   private GroundSet grounds = null;
   
   public GroundSet getGrounds()
   {
      if (this.grounds == null)
      {
         return GroundSet.EMPTY_SET;
      }
   
      return this.grounds;
   }

   public Game withGrounds(Ground... value)
   {
      if(value==null){
         return this;
      }
      for (Ground item : value)
      {
         if (item != null)
         {
            if (this.grounds == null)
            {
               this.grounds = new GroundSet();
            }
            
            boolean changed = this.grounds.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_GROUNDS, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutGrounds(Ground... value)
   {
      for (Ground item : value)
      {
         if ((this.grounds != null) && (item != null))
         {
            if (this.grounds.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_GROUNDS, item, null);
            }
         }
      }
      return this;
   }

   public Grass createGroundsGrass()
   {
      Grass value = new Grass();
      withGrounds(value);
      return value;
   } 
}
