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
   
package org.sdmlib.simple.model.modelling_a;

import org.sdmlib.simple.model.modelling_a.roomInterface;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.simple.model.modelling_a.Teacher;
import org.sdmlib.simple.model.modelling_a.util.PersonSet;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestModelCreation.java'>TestModelCreation.java</a>
 */
   public  class Room implements roomInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   private int number;

   public int getNumber()
   {
      return this.number;
   }
   
   public void setNumber(int value)
   {
      if (this.number != value) {
      
         int oldValue = this.number;
         this.number = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NUMBER, oldValue, value);
      }
   }
   
   public Room withNumber(int value)
   {
      setNumber(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getNumber());
      return result.substring(1);
   }


   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
      withoutCurrentPupils(this.getCurrentPupils().toArray(new Pupil[this.getCurrentPupils().size()]));
      setCurrentTeacher(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Person
    *              room                   persons
    * </pre>
    */
   
   public static final String PROPERTY_PERSONS = "persons";

   private PersonSet persons = null;
   
   public PersonSet getPersons()
   {
      if (this.persons == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.persons;
   }

   public Room withPersons(Person... value)
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
               item.withRoom(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         if ((this.persons != null) && (item != null))
         {
            if (this.persons.remove(item))
            {
               item.setRoom(null);
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

   public Pupil createPersonsPupil()
   {
      Pupil value = new Pupil();
      withPersons(value);
      return value;
   } 

   public Teacher createPersonsTeacher()
   {
      Teacher value = new Teacher();
      withPersons(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Pupil
    *              currentRoom                   currentPupils
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTPUPILS = "currentPupils";

   private PupilSet currentPupils = null;
   
   public PupilSet getCurrentPupils()
   {
      if (this.currentPupils == null)
      {
         return PupilSet.EMPTY_SET;
      }
   
      return this.currentPupils;
   }

   public Room withCurrentPupils(Pupil... value)
   {
      if(value==null){
         return this;
      }
      for (Pupil item : value)
      {
         if (item != null)
         {
            if (this.currentPupils == null)
            {
               this.currentPupils = new PupilSet();
            }
            
            boolean changed = this.currentPupils.add (item);

            if (changed)
            {
               item.withCurrentRoom(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTPUPILS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutCurrentPupils(Pupil... value)
   {
      for (Pupil item : value)
      {
         if ((this.currentPupils != null) && (item != null))
         {
            if (this.currentPupils.remove(item))
            {
               item.setCurrentRoom(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTPUPILS, item, null);
            }
         }
      }
      return this;
   }

   public Pupil createCurrentPupils()
   {
      Pupil value = new Pupil();
      withCurrentPupils(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Room ----------------------------------- Teacher
    *              currentRoom                   currentTeacher
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTTEACHER = "currentTeacher";

   private Teacher currentTeacher = null;

   public Teacher getCurrentTeacher()
   {
      return this.currentTeacher;
   }

   public boolean setCurrentTeacher(Teacher value)
   {
      boolean changed = false;
      
      if (this.currentTeacher != value)
      {
         Teacher oldValue = this.currentTeacher;
         
         if (this.currentTeacher != null)
         {
            this.currentTeacher = null;
            oldValue.setCurrentRoom(null);
         }
         
         this.currentTeacher = value;
         
         if (value != null)
         {
            value.withCurrentRoom(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTTEACHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withCurrentTeacher(Teacher value)
   {
      setCurrentTeacher(value);
      return this;
   } 

   public Teacher createCurrentTeacher()
   {
      Teacher value = new Teacher();
      withCurrentTeacher(value);
      return value;
   } 
}
