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
   
package org.sdmlib.simple.model.association_h;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.association_h.util.RoomSet;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.util.TeacherSet;
import org.sdmlib.simple.model.association_h.Teacher;
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
   
      withoutRooms(this.getRooms().toArray(new Room[this.getRooms().size()]));
      withoutTeachers(this.getTeachers().toArray(new Teacher[this.getTeachers().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Room
    *              person                   rooms
    * </pre>
    */
   
   public static final String PROPERTY_ROOMS = "rooms";

   private RoomSet rooms = null;
   
   public RoomSet getRooms()
   {
      if (this.rooms == null)
      {
         return RoomSet.EMPTY_SET;
      }
   
      return this.rooms;
   }

   public Person withRooms(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         if (item != null)
         {
            if (this.rooms == null)
            {
               this.rooms = new RoomSet();
            }
            
            boolean changed = this.rooms.add (item);

            if (changed)
            {
               item.withPerson(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutRooms(Room... value)
   {
      for (Room item : value)
      {
         if ((this.rooms != null) && (item != null))
         {
            if (this.rooms.remove(item))
            {
               item.setPerson(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, item, null);
            }
         }
      }
      return this;
   }

   public Room createRooms()
   {
      Room value = new Room();
      withRooms(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Teacher
    *              person                   teachers
    * </pre>
    */
   
   public static final String PROPERTY_TEACHERS = "teachers";

   private TeacherSet teachers = null;
   
   public TeacherSet getTeachers()
   {
      if (this.teachers == null)
      {
         return TeacherSet.EMPTY_SET;
      }
   
      return this.teachers;
   }

   public Person withTeachers(Teacher... value)
   {
      if(value==null){
         return this;
      }
      for (Teacher item : value)
      {
         if (item != null)
         {
            if (this.teachers == null)
            {
               this.teachers = new TeacherSet();
            }
            
            boolean changed = this.teachers.add (item);

            if (changed)
            {
               item.withPerson(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TEACHERS, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutTeachers(Teacher... value)
   {
      for (Teacher item : value)
      {
         if ((this.teachers != null) && (item != null))
         {
            if (this.teachers.remove(item))
            {
               item.setPerson(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TEACHERS, item, null);
            }
         }
      }
      return this;
   }

   public Teacher createTeachers()
   {
      Teacher value = new Teacher();
      withTeachers(value);
      return value;
   } 
}
