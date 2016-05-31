/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.simple.model.association_g;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.association_g.Person;
import org.sdmlib.simple.model.association_g.Teacher;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
 */
   public  class Room implements SendableEntity
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
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setPerson(null);
      setTeacher(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Room ----------------------------------- Person
    *              room                   person
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
            oldValue.setRoom(null);
         }
         
         this.person = value;
         
         if (value != null)
         {
            value.withRoom(this);
         }
         
         firePropertyChange(PROPERTY_PERSON, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withPerson(Person value)
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

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Room ----------------------------------- Teacher
    *              room                   teacher
    * </pre>
    */
   
   public static final String PROPERTY_TEACHER = "teacher";

   private Teacher teacher = null;

   public Teacher getTeacher()
   {
      return this.teacher;
   }

   public boolean setTeacher(Teacher value)
   {
      boolean changed = false;
      
      if (this.teacher != value)
      {
         Teacher oldValue = this.teacher;
         
         if (this.teacher != null)
         {
            this.teacher = null;
            oldValue.setRoom(null);
         }
         
         this.teacher = value;
         
         if (value != null)
         {
            value.withRoom(this);
         }
         
         firePropertyChange(PROPERTY_TEACHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withTeacher(Teacher value)
   {
      setTeacher(value);
      return this;
   } 

   public Teacher createTeacher()
   {
      Teacher value = new Teacher();
      withTeacher(value);
      return value;
   } 
}
