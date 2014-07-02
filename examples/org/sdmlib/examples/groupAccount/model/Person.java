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
   
package org.sdmlib.examples.groupAccount.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.examples.groupAccount.model.util.PersonSet;
import org.sdmlib.examples.groupAccount.model.util.ItemSet;

public class Person implements PropertyChangeInterface
{

   
   //==========================================================================
   
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

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setParent(null);
       withoutItem(this.getItem().toArray(new Item[this.getItem().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getBalance());
      return _.substring(1);
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

   
   public static final PersonSet EMPTY_SET = new PersonSet().withReadonly(true);

   
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

   public Item createItem()
   {
      Item value = new Item();
      withItem(value);
      return value;
   } 
}

