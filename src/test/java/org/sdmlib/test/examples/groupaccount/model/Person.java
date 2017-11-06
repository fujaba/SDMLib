/*
   Copyright (c) 2014 Stefan 
   
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.groupaccount.model.util.ItemSet;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
/**
 * 
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java'>GroupAccountClassModel.java</a>
 */
public class Person implements PropertyChangeInterface, PropertyChangeListener, SendableEntity
{

   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
    */
   public Person()
   {
      this.getPropertyChangeSupport().addPropertyChangeListener(PROPERTY_ITEM, this);
   }
   
   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
      setParent(null);
       withoutItem(this.getItem().toArray(new Item[this.getItem().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name = "Name?";

   public String getName()
   {
      return this.name;
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
   
   public Person withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      s.append(" ").append(this.getBalance());
      return s.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_BALANCE = "balance";
   
   private double balance;

   public double getBalance()
   {
      return this.balance;
   }
   
   public void setBalance(double value)
   {
      if (this.balance != value)
      {
         double oldValue = this.balance;
         this.balance = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BALANCE, oldValue, value);
      }
   }
   
   public Person withBalance(double value)
   {
      setBalance(value);
      return this;
   } 

   
   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- GroupAccount
    *              persons                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private GroupAccount parent = null;

   public GroupAccount getParent()
   {
      return this.parent;
   }

   public boolean setParent(GroupAccount value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         GroupAccount oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutPersons(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withPersons(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withParent(GroupAccount value)
   {
      setParent(value);
      return this;
   } 

   public GroupAccount createParent()
   {
      GroupAccount value = new GroupAccount();
      withParent(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Item
    *              buyer                   item
    * </pre>
    */
   
   public static final String PROPERTY_ITEM = "item";

   private ItemSet item = null;
   
   public ItemSet getItem()
   {
      if (this.item == null)
      {
         return Item.EMPTY_SET;
      }
   
      return this.item;
   }

   public Person withItem(Item... value)
   {
      if(value==null){
         return this;
      }
      for (Item item : value)
      {
         if (item != null)
         {
            if (this.item == null)
            {
               this.item = new ItemSet();
            }
            
            boolean changed = this.item.add (item);

            if (changed)
            {
               item.withBuyer(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEM, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutItem(Item... value)
   {
      for (Item item : value)
      {
         if ((this.item != null) && (item != null))
         {
            if (this.item.remove(item))
            {
               item.setBuyer(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEM, item, null);
            }
         }
         
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../.././src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
*/
   public Item createItem()
   {
      Item value = new Item();
      withItem(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TOTALPURCHASE = "totalPurchase";

   private double totalPurchase;
   
   public double getTotalPurchase()
   {
      return totalPurchase;
   }
   
   public void setTotalPurchase(double totalPurchase)
   {
      // this.totalPurchase = totalPurchase;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals(PROPERTY_ITEM) && evt.getNewValue() != null)
      {
         Item newItem = (Item) evt.getNewValue();
         newItem.getPropertyChangeSupport().addPropertyChangeListener(Item.PROPERTY_VALUE, this);
      }
      
      double oldValue = totalPurchase;
      totalPurchase = getItem().getValue().sum();
      
      if (oldValue != totalPurchase)
      {
         this.getPropertyChangeSupport().firePropertyChange(PROPERTY_TOTALPURCHASE, oldValue, totalPurchase);
      }
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

