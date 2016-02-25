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
import org.sdmlib.simple.model.association_g.Room;
import org.sdmlib.simple.model.association_g.Teacher;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
 */
   public  class Person implements SendableEntity
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
   
   
   public void removeYou()
   {
   
      setRoom(null);
      setTeacher(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
         
         if (this.room != null)
         {
            this.room = null;
            oldValue.setPerson(null);
         }
         
         this.room = value;
         
         if (value != null)
         {
            value.withPerson(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOM, oldValue, value);
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
    * Person ----------------------------------- Teacher
    *              person                   teacher
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
            oldValue.setPerson(null);
         }
         
         this.teacher = value;
         
         if (value != null)
         {
            value.withPerson(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEACHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withTeacher(Teacher value)
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
