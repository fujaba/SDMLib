/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.studyright;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.studyright.creators.AssignmentSet;
import java.beans.PropertyChangeListener;

public class Assignment implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         return getPoints();
      }

      if (PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         return getRoom();
      }

      if (PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return getStudents();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         setPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         setRoom((Room) value);
         return true;
      }

      if (PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         setStudents((Student) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setRoom(null);
      setStudents(null);
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
   
   public Assignment withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getPoints());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_POINTS = "points";
   
   private int points;

   public int getPoints()
   {
      return this.points;
   }
   
   public void setPoints(int value)
   {
      if (this.points != value)
      {
         int oldValue = this.points;
         this.points = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_POINTS, oldValue, value);
      }
   }
   
   public Assignment withPoints(int value)
   {
      setPoints(value);
      return this;
   } 

   
   public static final AssignmentSet EMPTY_SET = new AssignmentSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Assignment ----------------------------------- Room
    *              assignments                   room
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
            oldValue.withoutAssignments(this);
         }
         
         this.room = value;
         
         if (value != null)
         {
            value.withAssignments(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Assignment withRoom(Room value)
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
    *              many                       one
    * Assignment ----------------------------------- Student
    *              done                   students
    * </pre>
    */
   
   public static final String PROPERTY_STUDENTS = "students";
   
   private Student students = null;
   
   public Student getStudents()
   {
      return this.students;
   }
   
   public boolean setStudents(Student value)
   {
      boolean changed = false;
      
      if (this.students != value)
      {
         Student oldValue = this.students;
         
         if (this.students != null)
         {
            this.students = null;
            oldValue.withoutDone(this);
         }
         
         this.students = value;
         
         if (value != null)
         {
            value.withDone(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Assignment withStudents(Student value)
   {
      setStudents(value);
      return this;
   } 
   
   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   } 
}

