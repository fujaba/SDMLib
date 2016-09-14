/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.simpleEnumModel.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleEnumModel/SimpleClassModelWithEnumeration.java'>SimpleClassModelWithEnumeration.java</a>
*/
   public class Alex implements PropertyChangeInterface, SendableEntity
{

      //==========================================================================
      
      protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
      
      public PropertyChangeSupport getPropertyChangeSupport()
      {
         return listeners;
      }
      
      public boolean addPropertyChangeListener(PropertyChangeListener listener) 
      {
         getPropertyChangeSupport().addPropertyChangeListener(listener);
         return true;
      }
      
      public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
         getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
         return true;
      }
      
  	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   //==========================================================================
   
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "Name";
   
   private String Name;

   public String getName()
   {
      return this.Name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.Name, value))
      {
         String oldValue = this.Name;
         this.Name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Alex withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
