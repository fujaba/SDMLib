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
   
package org.sdmlib.test.examples.simpleModel.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.simpleModel.model.util.ObjectSet;
import org.sdmlib.test.examples.simpleModel.model.util.PersonSet;

import java.lang.Object;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.simpleModel.model.Person;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleModel/TestJsonForUniDirectionalAssoc.java'>TestJsonForUniDirectionalAssoc.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleModel/TestGenModel.java'>TestGenModel.java</a>
*/
   public class BigBrother implements PropertyChangeInterface, SendableEntity
{

   
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
      withoutKids(this.getKids().toArray(new Object[this.getKids().size()]));
      setNoOne(null);
      withoutSuspects(this.getSuspects().toArray(new Person[this.getSuspects().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * BigBrother ----------------------------------- Object
    *                                 kids
    * </pre>
    */
   
   public static final String PROPERTY_KIDS = "kids";

   private ObjectSet kids = null;
   
   public ObjectSet getKids()
   {
      if (this.kids == null)
      {
         return ObjectSet.EMPTY_SET;
      }
   
      return this.kids;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleModel/TestJsonForUniDirectionalAssoc.java'>TestJsonForUniDirectionalAssoc.java</a>
*/
   public BigBrother withKids(Object... value)
   {
      if(value==null){
         return this;
      }
      for (Object item : value)
      {
         if (item != null)
         {
            if (this.kids == null)
            {
               this.kids = new ObjectSet();
            }
            
            boolean changed = this.kids.add (item);

            if (changed)
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDS, null, item);
            }
         }
      }
      return this;
   } 

   public BigBrother withoutKids(Object... value)
   {
      for (Object item : value)
      {
         if ((this.kids != null) && (item != null))
         {
            if (this.kids.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDS, item, null);
            }
         }
      }
      return this;
   }

   public Object createKids()
   {
      Object value = new Object();
      withKids(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * BigBrother ----------------------------------- Person
    *                                 noOne
    * </pre>
    */
   
   public static final String PROPERTY_NOONE = "noOne";

   private Person noOne = null;

   public Person getNoOne()
   {
      return this.noOne;
   }

   public boolean setNoOne(Person value)
   {
      boolean changed = false;
      
      if (this.noOne != value)
      {
         Person oldValue = this.noOne;
         
         
         this.noOne = value;
         
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NOONE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public BigBrother withNoOne(Person value)
   {
      setNoOne(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleModel/TestJsonForUniDirectionalAssoc.java'>TestJsonForUniDirectionalAssoc.java</a>
*/
   public Person createNoOne()
   {
      Person value = new Person();
      withNoOne(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * BigBrother ----------------------------------- Person
    *                                 suspects
    * </pre>
    */
   
   public static final String PROPERTY_SUSPECTS = "suspects";

   private PersonSet suspects = null;
   
   public PersonSet getSuspects()
   {
      if (this.suspects == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.suspects;
   }

   public BigBrother withSuspects(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.suspects == null)
            {
               this.suspects = new PersonSet();
            }
            
            boolean changed = this.suspects.add (item);

            if (changed)
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUSPECTS, null, item);
            }
         }
      }
      return this;
   } 

   public BigBrother withoutSuspects(Person... value)
   {
      for (Person item : value)
      {
         if ((this.suspects != null) && (item != null))
         {
            if (this.suspects.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUSPECTS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/simpleModel/TestJsonForUniDirectionalAssoc.java'>TestJsonForUniDirectionalAssoc.java</a>
*/
   public Person createSuspects()
   {
      Person value = new Person();
      withSuspects(value);
      return value;
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
