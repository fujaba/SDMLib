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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsModel.java'>StudyRightWithAssignmentsModel.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public  class University implements SendableEntity
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
      withoutStudents(this.getStudents().toArray(new Student[this.getStudents().size()]));
      withoutRooms(this.getRooms().toArray(new Room[this.getRooms().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
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
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * University ----------------------------------- Student
    *              university                   students
    * </pre>
    */
   
   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;
   
     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public StudentSet getStudents()
   {
      if (this.students == null)
      {
         return StudentSet.EMPTY_SET;
      }
   
      return this.students;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public University withStudents(Student... value)
   {
      if(value==null){
         return this;
      }
      for (Student item : value)
      {
         if (item != null)
         {
            if (this.students == null)
            {
               this.students = new StudentSet();
            }
            
            boolean changed = this.students.add (item);

            if (changed)
            {
               item.withUniversity(this);
               firePropertyChange(PROPERTY_STUDENTS, null, item);
            }
         }
      }
      return this;
   } 

   public University withoutStudents(Student... value)
   {
      for (Student item : value)
      {
         if ((this.students != null) && (item != null))
         {
            if (this.students.remove(item))
            {
               item.setUniversity(null);
               firePropertyChange(PROPERTY_STUDENTS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public TeachingAssistant createStudentsTeachingAssistant()
   {
      TeachingAssistant value = new TeachingAssistant();
      withStudents(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * University ----------------------------------- Room
    *              university                   rooms
    * </pre>
    */
   
   public static final String PROPERTY_ROOMS = "rooms";

   private RoomSet rooms = null;
   
     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public RoomSet getRooms()
   {
      if (this.rooms == null)
      {
         return RoomSet.EMPTY_SET;
      }
   
      return this.rooms;
   }

   public University withRooms(Room... value)
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
               item.withUniversity(this);
               firePropertyChange(PROPERTY_ROOMS, null, item);
            }
         }
      }
      return this;
   } 

   public University withoutRooms(Room... value)
   {
      for (Room item : value)
      {
         if ((this.rooms != null) && (item != null))
         {
            if (this.rooms.remove(item))
            {
               item.setUniversity(null);
               firePropertyChange(PROPERTY_ROOMS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public Room createRooms()
   {
      Room value = new Room();
      withRooms(value);
      return value;
   } 
}
