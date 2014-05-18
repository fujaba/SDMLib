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
   
package org.sdmlib.examples.groupAccount.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.examples.groupAccount.model.util.ItemSet;
import org.sdmlib.examples.groupAccount.model.util.PersonSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class GroupAccount implements PropertyChangeInterface
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
      removeAllFromPersons();
      removeAllFromItems();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public double getTaskNames( double p0, String p1 )
   {
      return 0;
   }

   
   //==========================================================================
   
   public void updateBalances(  )
   {
      double total = this.getItems().getValue().sum();
      double share = total / this.getPersons().size();
      
      for (Person person : this.getPersons())
      {
         double expenditures = person.getItems().getValue().sum();
         person.setBalance(expenditures - share);
      }
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

   public boolean addToPersons(Person value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.persons == null)
         {
            this.persons = new PersonSet();
         }
         
         changed = this.persons.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromPersons(Person value)
   {
      boolean changed = false;
      
      if ((this.persons != null) && (value != null))
      {
         changed = this.persons.remove (value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, value, null);
         }
      }
         
      return changed;   
   }

   public GroupAccount withPersons(Person... value)
   {
      for (Person item : value)
      {
         addToPersons(item);
      }
      return this;
   } 

   public GroupAccount withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         removeFromPersons(item);
      }
      return this;
   }

   public void removeAllFromPersons()
   {
      LinkedHashSet<Person> tmpSet = new LinkedHashSet<Person>(this.getPersons());
   
      for (Person value : tmpSet)
      {
         this.removeFromPersons(value);
      }
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
    *              parent                   items
    * </pre>
    */
   
   public static final String PROPERTY_ITEMS = "items";

   private ItemSet items = null;
   
   public ItemSet getItems()
   {
      if (this.items == null)
      {
         return Item.EMPTY_SET;
      }
   
      return this.items;
   }

   public boolean addToItems(Item value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.items == null)
         {
            this.items = new ItemSet();
         }
         
         changed = this.items.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromItems(Item value)
   {
      boolean changed = false;
      
      if ((this.items != null) && (value != null))
      {
         changed = this.items.remove (value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, value, null);
         }
      }
         
      return changed;   
   }

   public GroupAccount withItems(Item... value)
   {
      for (Item item : value)
      {
         addToItems(item);
      }
      return this;
   } 

   public GroupAccount withoutItems(Item... value)
   {
      for (Item item : value)
      {
         removeFromItems(item);
      }
      return this;
   }

   public void removeAllFromItems()
   {
      LinkedHashSet<Item> tmpSet = new LinkedHashSet<Item>(this.getItems());
   
      for (Item value : tmpSet)
      {
         this.removeFromItems(value);
      }
   }

   public Item createItems()
   {
      Item value = new Item();
      withItems(value);
      return value;
   } 
}

