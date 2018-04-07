/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.test.examples.groupaccount.model;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.EntityUtil;
import org.sdmlib.test.examples.groupaccount.model.Person;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java'>GroupAccountClassModel.java</a>
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 */
   public  class Item implements SendableEntity
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
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(listener);
   	}
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
      setPerson(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DESCRIPTION = "description";
   
   private String description;

   public String getDescription()
   {
      return this.description;
   }
   
   public void setDescription(String value)
   {
      if ( ! EntityUtil.stringEquals(this.description, value)) {
      
         String oldValue = this.description;
         this.description = value;
         this.firePropertyChange(PROPERTY_DESCRIPTION, oldValue, value);
      }
   }
   
   public Item withDescription(String value)
   {
      setDescription(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getDescription());
      result.append(" ").append(this.getPrice());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PRICE = "price";
   
   private double price;

   public double getPrice()
   {
      return this.price;
   }
   
   public void setPrice(double value)
   {
      if (this.price != value) {
      
         double oldValue = this.price;
         this.price = value;
         this.firePropertyChange(PROPERTY_PRICE, oldValue, value);
      }
   }
   
   public Item withPrice(double value)
   {
      setPrice(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Item ----------------------------------- Person
    *              items                   person
    * </pre>
    */
   
   public static final String PROPERTY_PERSON = "person";

   private Person person = null;

   public Person getPerson()
   {
      return this.person;
   }

   public boolean setPerson(Person value)
   {
      boolean changed = false;
      
      if (this.person != value)
      {
         Person oldValue = this.person;
         
         if (this.person != null)
         {
            this.person = null;
            oldValue.withoutItems(this);
         }
         
         this.person = value;
         
         if (value != null)
         {
            value.withItems(this);
         }
         
         firePropertyChange(PROPERTY_PERSON, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withPerson(Person value)
   {
      setPerson(value);
      return this;
   } 

   public Person createPerson()
   {
      Person value = new Person();
      withPerson(value);
      return value;
   } 
}
