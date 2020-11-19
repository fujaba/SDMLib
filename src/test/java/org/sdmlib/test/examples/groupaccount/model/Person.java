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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.test.examples.groupaccount.model.util.ItemSet;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java'>GroupAccountClassModel.java</a>
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 */
   public  class Person implements SendableEntity
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
      setParty(null);
      withoutItems(this.getItems().toArray(new Item[this.getItems().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Person withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getSaldo());
      result.append(" ").append(this.getTotal());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_SALDO = "saldo";
   
   private double saldo;

   public double getSaldo()
   {
      return this.saldo;
   }
   
   public void setSaldo(double value)
   {
      if (this.saldo != value) {
      
         double oldValue = this.saldo;
         this.saldo = value;
         this.firePropertyChange(PROPERTY_SALDO, oldValue, value);
      }
   }
   
   public Person withSaldo(double value)
   {
      setSaldo(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TOTAL = "total";
   
   private double total;

   public double getTotal()
   {
      return this.total;
   }
   
   public void setTotal(double value)
   {
      if (this.total != value) {
      
         double oldValue = this.total;
         this.total = value;
         this.firePropertyChange(PROPERTY_TOTAL, oldValue, value);
      }
   }
   
   public Person withTotal(double value)
   {
      setTotal(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Party
    *              guests                   party
    * </pre>
    */
   
   public static final String PROPERTY_PARTY = "party";

   private Party party = null;

   public Party getParty()
   {
      return this.party;
   }

   public boolean setParty(Party value)
   {
      boolean changed = false;
      
      if (this.party != value)
      {
         Party oldValue = this.party;
         
         if (this.party != null)
         {
            this.party = null;
            oldValue.withoutGuests(this);
         }
         
         this.party = value;
         
         if (value != null)
         {
            value.withGuests(this);
         }
         
         firePropertyChange(PROPERTY_PARTY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withParty(Party value)
   {
      setParty(value);
      return this;
   } 

   public Party createParty()
   {
      Party value = new Party();
      withParty(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Item
    *              person                   items
    * </pre>
    */
   
   public static final String PROPERTY_ITEMS = "items";

   private ItemSet items = null;
   
   public ItemSet getItems()
   {
      if (this.items == null)
      {
         return ItemSet.EMPTY_SET;
      }
   
      return this.items;
   }

   public Person withItems(Item... value)
   {
      if(value==null){
         return this;
      }
      for (Item item : value)
      {
         if (item != null)
         {
            if (this.items == null)
            {
               this.items = new ItemSet();
            }
            
            boolean changed = this.items.add (item);

            if (changed)
            {
               item.withPerson(this);
               firePropertyChange(PROPERTY_ITEMS, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutItems(Item... value)
   {
      for (Item item : value)
      {
         if ((this.items != null) && (item != null))
         {
            if (this.items.remove(item))
            {
               item.setPerson(null);
               firePropertyChange(PROPERTY_ITEMS, item, null);
            }
         }
      }
      return this;
   }

   public Item createItems()
   {
      Item value = new Item();
      withItems(value);
      return value;
   } 
}
