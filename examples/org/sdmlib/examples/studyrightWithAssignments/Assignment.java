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
   
package org.sdmlib.examples.studyrightWithAssignments;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Assignment implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_CONTENT.equalsIgnoreCase(attrName))
      {
         return getContent();
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
      if (PROPERTY_CONTENT.equalsIgnoreCase(attrName))
      {
         setContent((String) value);
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
         addToStudents((Student) value);
         return true;
      }
      
      if ((PROPERTY_STUDENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStudents((Student) value);
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
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setRoom(null);
      removeAllFromStudents();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CONTENT = "content";
   
   private String content;

   public String getContent()
   {
      return this.content;
   }
   
   public void setContent(String value)
   {
      if ( ! StrUtil.stringEquals(this.content, value))
      {
         String oldValue = this.content;
         this.content = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CONTENT, oldValue, value);
      }
   }
   
   public Assignment withContent(String value)
   {
      setContent(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getContent());
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
    *              many                       many
    * Assignment ----------------------------------- Student
    *              done                   students
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
            value.withDone(this);
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
         changed = this.students.remove (value);
         
         if (changed)
         {
            value.withoutDone(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Assignment withStudents(Student... value)
   {
      for (Student item : value)
      {
         addToStudents(item);
      }
      return this;
   } 
   
   public Assignment withoutStudents(Student... value)
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

