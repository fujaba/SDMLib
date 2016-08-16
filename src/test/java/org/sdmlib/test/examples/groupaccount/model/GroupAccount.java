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

import org.junit.Assert;
import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
import org.sdmlib.test.examples.groupaccount.model.Person;
import de.uniks.networkparser.interfaces.SendableEntity;
/**
 * 
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java'>GroupAccountClassModel.java</a>
 */
public class GroupAccount implements PropertyChangeInterface, PropertyChangeListener, SendableEntity
{
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
    */
   public GroupAccount()
   {
      this.getPropertyChangeSupport().addPropertyChangeListener(PROPERTY_PERSONS, this);
   }

   //==========================================================================

   /**
    * Use like:<br><pre>
    *    GroupAccount g1 = new GroupAccount();
    *    ...
    *    g1.updateBalances();
    *</pre>    
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
    */
   @SuppressWarnings("deprecation")
   public void updateBalances(  )
   {
      // compute share
      double share = totalPurchase / this.getPersons().size();
      
      for (Person person : this.getPersons())
      {
         double personExpenses  = person.getItem().getValue().sum();
         person.setBalance(personExpenses - share);
      }
      
      Assert.assertEquals("Balance should sum up to zero", 0.0, this.getPersons().getBalance().sum(), 0.00001);
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

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
  
   //==========================================================================
   
   
   public void removeYou()
   {
       withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
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
   
     /**
    * 
    * @see <a href='../../../../../../../../.././src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
*/
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

   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
    */
   public Person createPersons()
   {
      Person value = new Person();
      withPersons(value);
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

   
   //==========================================================================
   
   public static final String PROPERTY_TASK = "task";
   
   private String task = "";

   public String getTask()
   {
      return this.task;
   }
   
   public void setTask(String value)
   {
      if ( ! StrUtil.stringEquals(this.task, value)) {
      
         String oldValue = this.task;
         this.task = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASK, oldValue, value);
      }
   }
   
   public GroupAccount withTask(String value)
   {
      setTask(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getTask());
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
