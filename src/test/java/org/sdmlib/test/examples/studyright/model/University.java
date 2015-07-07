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
   
package org.sdmlib.test.examples.studyright.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.studyright.model.util.RoomSet;
import org.sdmlib.test.examples.studyright.model.util.StudentSet;

public class University implements PropertyChangeInterface
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
      withoutRooms(this.getRooms().toArray(new Room[this.getRooms().size()]));
      withoutStudents(this.getStudents().toArray(new Student[this.getStudents().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public University withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      return s.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * University ----------------------------------- Room
    *              uni                   rooms
    * </pre>
    */
   
   public static final String PROPERTY_ROOMS = "rooms";

   private RoomSet rooms = null;
   
   public RoomSet getRooms()
   {
      if (this.rooms == null)
      {
         return Room.EMPTY_SET;
      }
   
      return this.rooms;
   }

   public boolean addToRooms(Room value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.rooms == null)
         {
            this.rooms = new RoomSet();
         }
         
         changed = this.rooms.add (value);
         
         if (changed)
         {
            value.withUni(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromRooms(Room value)
   {
      boolean changed = false;
      
      if ((this.rooms != null) && (value != null))
      {
         changed = this.rooms.remove(value);
         
         if (changed)
         {
            value.setUni(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, value, null);
         }
      }
         
      return changed;   
   }

   public University withRooms(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         addToRooms(item);
      }
      return this;
   } 

   public University withoutRooms(Room... value)
   {
      for (Room item : value)
      {
         removeFromRooms(item);
      }
      return this;
   }

   public void removeAllFromRooms()
   {
      LinkedHashSet<Room> tmpSet = new LinkedHashSet<Room>(this.getRooms());
   
      for (Room value : tmpSet)
      {
         this.removeFromRooms(value);
      }
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
    * University ----------------------------------- Student
    *              uni                   students
    * </pre>
    */
   
   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;
   
   public StudentSet getStudents()
   {
      if (this.students == null)
      {
         return Student.EMPTY_SET;
      }
   
      return this.students;
   }

   public boolean addToStudents(Student value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.students == null)
         {
            this.students = new StudentSet();
         }
         
         changed = this.students.add (value);
         
         if (changed)
         {
            value.withUni(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromStudents(Student value)
   {
      boolean changed = false;
      
      if ((this.students != null) && (value != null))
      {
         changed = this.students.remove(value);
         
         if (changed)
         {
            value.setUni(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, value, null);
         }
      }
         
      return changed;   
   }

   public University withStudents(Student... value)
   {
      if(value==null){
         return this;
      }
      for (Student item : value)
      {
         addToStudents(item);
      }
      return this;
   } 

   public University withoutStudents(Student... value)
   {
      for (Student item : value)
      {
         removeFromStudents(item);
      }
      return this;
   }

   public void removeAllFromStudents()
   {
      LinkedHashSet<Student> tmpSet = new LinkedHashSet<Student>(this.getStudents());
   
      for (Student value : tmpSet)
      {
         this.removeFromStudents(value);
      }
   }

   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   } 
}
