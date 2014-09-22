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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import org.sdmlib.examples.groupAccount.model.util.PersonSet;
import org.sdmlib.examples.groupAccount.model.util.ItemSet;
import org.sdmlib.examples.groupAccount.model.Person;
import java.util.LinkedHashSet;

public class GroupAccount implements PropertyChangeInterface, PropertyChangeListener
{
   public GroupAccount()
   {
      this.getPropertyChangeSupport().addPropertyChangeListener(PROPERTY_PERSONS, this);
   }
   
   //==========================================================================
   
   public double getTaskNames( double p0, String p1 )
   {
      return 0;
   }

   
   //==========================================================================
   
   public void updateBalances(  )
   {
      // compute share
      double share = totalPurchase / this.getPersons().size();
      
      for (Person person : this.getPersons())
      {
         double personExpenses  = person.getItem().getValue().sum();
         person.setBalance(personExpenses - share);
      }
   }

   
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
       withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
       withoutItem(this.getItem().toArray(new Item[this.getItem().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GroupAccount ----------------------------------- Person
    *              parent                   persons
    * </pre>
    */
   
   public static final String PROPERTY_PERSONS = "persons";

   private PersonSet persons = null;
   
   public PersonSet getPersons()
   {
      if (this.persons == null)
      {
         return Person.EMPTY_SET;
      }
   
      return this.persons;
   }

   public GroupAccount withPersons(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.persons == null)
            {
               this.persons = new PersonSet();
            }
            
            boolean changed = this.persons.add (item);

            if (changed)
            {
               item.withParent(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, null, item);
            }
         }
      }
      return this;
   } 

   public GroupAccount withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         if ((this.persons != null) && (item != null))
         {
            if (this.persons.remove(item))
            {
               item.setParent(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, item, null);
            }
         }
         
      }
      return this;
   }

   public Person createPersons()
   {
      Person value = new Person();
      withPersons(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GroupAccount ----------------------------------- Item
    *              parent                   item
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

   public GroupAccount withItem(Item... value)
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
               item.withParent(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEM, null, item);
            }
         }
      }
      return this;
   } 

   public GroupAccount withoutItem(Item... value)
   {
      for (Item item : value)
      {
         if ((this.item != null) && (item != null))
         {
            if (this.item.remove(item))
            {
               item.setParent(null);
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
      if (evt.getPropertyName().equals(PROPERTY_PERSONS) && evt.getNewValue() != null)
      {
         Person newPerson = (Person) evt.getNewValue();
         newPerson.getPropertyChangeSupport().addPropertyChangeListener(Person.PROPERTY_TOTALPURCHASE, this);
      }
      
      double oldValue = totalPurchase;
      totalPurchase = getPersons().getTotalPurchase().sum();
      
      if (oldValue != totalPurchase)
      {
         this.getPropertyChangeSupport().firePropertyChange(PROPERTY_TOTALPURCHASE, oldValue, totalPurchase);
      }
      
      oldValue = averagePurchase;
      averagePurchase = totalPurchase / getPersons().size();
      if (oldValue != averagePurchase)
      {
         this.getPropertyChangeSupport().firePropertyChange(PROPERTY_AVERAGEPURCHASE, oldValue, averagePurchase);
      }
      
      updateBalances();
   }
   
   //==========================================================================
   
   public static final String PROPERTY_AVERAGEPURCHASE = "averagePurchase";

   private double averagePurchase;
   
   public double getAveragePurchase()
   {
      return averagePurchase;
   }
   
   public void setAveragePurchase(double averagePurchase)
   {
      // this.totalPurchase = totalPurchase;
   }
}
