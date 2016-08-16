/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.association_c;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.association_c.Room;
import org.sdmlib.simple.model.association_c.util.PersonSet;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
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
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
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
      setRoom(null);
      setPrevPerson(null);
      setNextPerson(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Person ----------------------------------- Room
    *              person                   room
    * </pre>
    */
   
   public static final String PROPERTY_ROOM = "room";

   private Room room = null;

   public Room getRoom()
   {
      return this.room;
   }

   public boolean setRoom(Room value)
   {
      boolean changed = false;
      
      if (this.room != value)
      {
         Room oldValue = this.room;
         
         
         this.room = value;
         
         
         firePropertyChange(PROPERTY_ROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withRoom(Room value)
   {
      setRoom(value);
      return this;
   } 

   public Room createRoom()
   {
      Room value = new Room();
      withRoom(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Person ----------------------------------- Person
    *              person                   prevPerson
    * </pre>
    */
   
   public static final String PROPERTY_PREVPERSON = "prevPerson";

   private Person prevPerson = null;

   public Person getPrevPerson()
   {
      return this.prevPerson;
   }
   public PersonSet getPrevPersonTransitive()
   {
      PersonSet result = new PersonSet().with(this);
      return result.getPrevPersonTransitive();
   }


   public boolean setPrevPerson(Person value)
   {
      boolean changed = false;
      
      if (this.prevPerson != value)
      {
         Person oldValue = this.prevPerson;
         
         
         this.prevPerson = value;
         
         
         firePropertyChange(PROPERTY_PREVPERSON, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withPrevPerson(Person value)
   {
      setPrevPerson(value);
      return this;
   } 

   public Person createPrevPerson()
   {
      Person value = new Person();
      withPrevPerson(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Person ----------------------------------- Person
    *              person                   nextPerson
    * </pre>
    */
   
   public static final String PROPERTY_NEXTPERSON = "nextPerson";

   private Person nextPerson = null;

   public Person getNextPerson()
   {
      return this.nextPerson;
   }
   public PersonSet getNextPersonTransitive()
   {
      PersonSet result = new PersonSet().with(this);
      return result.getNextPersonTransitive();
   }


   public boolean setNextPerson(Person value)
   {
      boolean changed = false;
      
      if (this.nextPerson != value)
      {
         Person oldValue = this.nextPerson;
         
         
         this.nextPerson = value;
         
         
         firePropertyChange(PROPERTY_NEXTPERSON, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withNextPerson(Person value)
   {
      setNextPerson(value);
      return this;
   } 

   public Person createNextPerson()
   {
      Person value = new Person();
      withNextPerson(value);
      return value;
   } 
}
