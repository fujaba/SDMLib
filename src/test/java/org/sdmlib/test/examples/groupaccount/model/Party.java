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
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
import org.sdmlib.test.examples.groupaccount.model.Person;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java'>GroupAccountClassModel.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYamlMerging
 */
   public  class Party implements SendableEntity
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
      withoutGuests(this.getGuests().toArray(new Person[this.getGuests().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PARTYNAME = "partyName";
   
   private String partyName;

   public String getPartyName()
   {
      return this.partyName;
   }
   
   public void setPartyName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.partyName, value)) {
      
         String oldValue = this.partyName;
         this.partyName = value;
         this.firePropertyChange(PROPERTY_PARTYNAME, oldValue, value);
      }
   }
   
   public Party withPartyName(String value)
   {
      setPartyName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getPartyName());
      result.append(" ").append(this.getShare());
      result.append(" ").append(this.getTotal());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_SHARE = "share";
   
   private double share;

   public double getShare()
   {
      return this.share;
   }
   
   public void setShare(double value)
   {
      if (this.share != value) {
      
         double oldValue = this.share;
         this.share = value;
         this.firePropertyChange(PROPERTY_SHARE, oldValue, value);
      }
   }
   
   public Party withShare(double value)
   {
      setShare(value);
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
   
   public Party withTotal(double value)
   {
      setTotal(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Party ----------------------------------- Person
    *              party                   guests
    * </pre>
    */
   
   public static final String PROPERTY_GUESTS = "guests";

   private PersonSet guests = null;
   
   public PersonSet getGuests()
   {
      if (this.guests == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.guests;
   }

   public Party withGuests(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.guests == null)
            {
               this.guests = new PersonSet();
            }
            
            boolean changed = this.guests.add (item);

            if (changed)
            {
               item.withParty(this);
               firePropertyChange(PROPERTY_GUESTS, null, item);
            }
         }
      }
      return this;
   } 

   public Party withoutGuests(Person... value)
   {
      for (Person item : value)
      {
         if ((this.guests != null) && (item != null))
         {
            if (this.guests.remove(item))
            {
               item.setParty(null);
               firePropertyChange(PROPERTY_GUESTS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>GroupAccountTests.java</a>
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYamlMerging
 */
   public Person createGuests()
   {
      Person value = new Person();
      withGuests(value);
      return value;
   } 
}
