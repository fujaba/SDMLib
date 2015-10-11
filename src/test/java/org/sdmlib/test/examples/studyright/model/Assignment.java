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
import org.sdmlib.test.examples.studyright.model.util.AssignmentSet;
import org.sdmlib.test.examples.studyright.model.util.RoomSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyright/StudyRightModel.java'>StudyRightModel.java</a>
*/
   public class Assignment implements PropertyChangeInterface
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
      removeAllFromAssignments();
      setStudents(null);
      withoutAssignments(this.getAssignments().toArray(new Room[this.getAssignments().size()]));
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


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      s.append(" ").append(this.getPoints());
      return s.substring(1);
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

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Assignment ----------------------------------- Room
    *              room                   assignments
    * </pre>
    */
   
   public static final String PROPERTY_ASSIGNMENTS = "assignments";

   private RoomSet assignments = null;
   
   public RoomSet getAssignments()
   {
      if (this.assignments == null)
      {
         return Room.EMPTY_SET;
      }
   
      return this.assignments;
   }

   public boolean addToAssignments(Room value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.assignments == null)
         {
            this.assignments = new RoomSet();
         }
         
         changed = this.assignments.add (value);
         
         if (changed)
         {
            value.withRoom(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromAssignments(Room value)
   {
      boolean changed = false;
      
      if ((this.assignments != null) && (value != null))
      {
         changed = this.assignments.remove(value);
         
         if (changed)
         {
            value.setRoom(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTS, value, null);
         }
      }
         
      return changed;   
   }

   public Assignment withAssignments(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         addToAssignments(item);
      }
      return this;
   } 

   public Assignment withoutAssignments(Room... value)
   {
      for (Room item : value)
      {
         removeFromAssignments(item);
      }
      return this;
   }

   public void removeAllFromAssignments()
   {
      LinkedHashSet<Room> tmpSet = new LinkedHashSet<Room>(this.getAssignments());
   
      for (Room value : tmpSet)
      {
         this.removeFromAssignments(value);
      }
   }

   public Room createAssignments()
   {
      Room value = new Room();
      withAssignments(value);
      return value;
   } 

   
   public static final AssignmentSet EMPTY_SET = new AssignmentSet();

   
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

