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
   
package org.sdmlib.simple.model.abstract_A;

import org.sdmlib.simple.model.abstract_A.Human;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAbstractClazz.java'>TestAbstractClazz.java</a>
 */
   public abstract class Person implements Human, SendableEntity
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

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Human ----------------------------------- Person
    *              owner                   has
    * </pre>
    */
   
   public static final String PROPERTY_HAS = "has";

   private Person has = null;

   public Person getHas()
   {
      return this.has;
   }

   public boolean setHas(Person value)
   {
      boolean changed = false;
      
      if (this.has != value)
      {
         Person oldValue = this.has;
         
         if (this.has != null)
         {
            this.has = null;
            oldValue.setOwner(null);
         }
         
         this.has = value;
         
         if (value != null)
         {
            value.withOwner(this);
         }
         
         firePropertyChange(PROPERTY_HAS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Human withHas(Person value)
   {
      setHas(value);
      return this;
   } 

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setOwner(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Person ----------------------------------- Human
    *              has                   owner
    * </pre>
    */
   
   public static final String PROPERTY_OWNER = "owner";

   private Human owner = null;

   public Human getOwner()
   {
      return this.owner;
   }

   public boolean setOwner(Human value)
   {
      boolean changed = false;
      
      if (this.owner != value)
      {
         Human oldValue = this.owner;
         
         if (this.owner != null)
         {
            this.owner = null;
            oldValue.setHas(null);
         }
         
         this.owner = value;
         
         if (value != null)
         {
            value.withHas(this);
         }
         
         firePropertyChange(PROPERTY_OWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withOwner(Human value)
   {
      setOwner(value);
      return this;
   } 
}
