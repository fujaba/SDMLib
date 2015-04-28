/*
   Copyright (c) 2015 Olaf Gunkel 
   
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
   
package org.sdmlib.examples.studyrightWithAssignments.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.TeachingAssistantSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class Room implements PropertyChangeInterface
{

   
   //==========================================================================
   public void findPath( int String )
   {
      
   }

   
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
      setUniversity(null);
      withoutDoors(this.getDoors().toArray(new Room[this.getDoors().size()]));
      withoutStudents(this.getStudents().toArray(new Student[this.getStudents().size()]));
      withoutAssignments(this.getAssignments().toArray(new Assignment[this.getAssignments().size()]));
      withoutTas(this.getTas().toArray(new TeachingAssistant[this.getTas().size()]));
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
   
   public Room withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getTopic());
      result.append(" ").append(this.getCredits());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TOPIC = "topic";
   
   private String topic;

   public String getTopic()
   {
      return this.topic;
   }
   
   public void setTopic(String value)
   {
      if ( ! StrUtil.stringEquals(this.topic, value))
      {
         String oldValue = this.topic;
         this.topic = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TOPIC, oldValue, value);
      }
   }
   
   public Room withTopic(String value)
   {
      setTopic(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CREDITS = "credits";
   
   private int credits;

   public int getCredits()
   {
      return this.credits;
   }
   
   public void setCredits(int value)
   {
      if (this.credits != value)
      {
         int oldValue = this.credits;
         this.credits = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CREDITS, oldValue, value);
      }
   }
   
   public Room withCredits(int value)
   {
      setCredits(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Room ----------------------------------- University
    *              rooms                   university
    * </pre>
    */
   
   public static final String PROPERTY_UNIVERSITY = "university";

   private University university = null;

   public University getUniversity()
   {
      return this.university;
   }

   public boolean setUniversity(University value)
   {
      boolean changed = false;
      
      if (this.university != value)
      {
         University oldValue = this.university;
         
         if (this.university != null)
         {
            this.university = null;
            oldValue.withoutRooms(this);
         }
         
         this.university = value;
         
         if (value != null)
         {
            value.withRooms(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withUniversity(University value)
   {
      setUniversity(value);
      return this;
   } 

   public University createUniversity()
   {
      University value = new University();
      withUniversity(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Room ----------------------------------- Room
    *              doors                   doors
    * </pre>
    */
   
   public static final String PROPERTY_DOORS = "doors";

   private RoomSet doors = null;
   
   public RoomSet getDoors()
   {
      if (this.doors == null)
      {
         return RoomSet.EMPTY_SET;
      }
   
      return this.doors;
   }
   public RoomSet getDoorsTransitive()
   {
      RoomSet result = new RoomSet().with(this);
      return result.getDoorsTransitive();
   }


   public Room withDoors(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         if (item != null)
         {
            if (this.doors == null)
            {
               this.doors = new RoomSet();
            }
            
            boolean changed = this.doors.add (item);

            if (changed)
            {
               item.withDoors(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DOORS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutDoors(Room... value)
   {
      for (Room item : value)
      {
         if ((this.doors != null) && (item != null))
         {
            if (this.doors.remove(item))
            {
               item.withoutDoors(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DOORS, item, null);
            }
         }
      }
      return this;
   }

   public Room createDoors()
   {
      Room value = new Room();
      withDoors(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Student
    *              in                   students
    * </pre>
    */
   
   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;
   
   public StudentSet getStudents()
   {
      if (this.students == null)
      {
         return StudentSet.EMPTY_SET;
      }
   
      return this.students;
   }

   public Room withStudents(Student... value)
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
               item.withIn(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutStudents(Student... value)
   {
      for (Student item : value)
      {
         if ((this.students != null) && (item != null))
         {
            if (this.students.remove(item))
            {
               item.setIn(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, item, null);
            }
         }
      }
      return this;
   }

   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   } 

   public TeachingAssistant createStudentsTeachingAssistant()
   {
      TeachingAssistant value = new TeachingAssistant();
      withStudents(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Assignment
    *              room                   assignments
    * </pre>
    */
   
   public static final String PROPERTY_ASSIGNMENTS = "assignments";

   private AssignmentSet assignments = null;
   
   public AssignmentSet getAssignments()
   {
      if (this.assignments == null)
      {
         return AssignmentSet.EMPTY_SET;
      }
   
      return this.assignments;
   }

   public Room withAssignments(Assignment... value)
   {
      if(value==null){
         return this;
      }
      for (Assignment item : value)
      {
         if (item != null)
         {
            if (this.assignments == null)
            {
               this.assignments = new AssignmentSet();
            }
            
            boolean changed = this.assignments.add (item);

            if (changed)
            {
               item.withRoom(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutAssignments(Assignment... value)
   {
      for (Assignment item : value)
      {
         if ((this.assignments != null) && (item != null))
         {
            if (this.assignments.remove(item))
            {
               item.setRoom(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTS, item, null);
            }
         }
      }
      return this;
   }

   public Assignment createAssignments()
   {
      Assignment value = new Assignment();
      withAssignments(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- TeachingAssistant
    *              room                   tas
    * </pre>
    */
   
   public static final String PROPERTY_TAS = "tas";

   private TeachingAssistantSet tas = null;
   
   public TeachingAssistantSet getTas()
   {
      if (this.tas == null)
      {
         return TeachingAssistantSet.EMPTY_SET;
      }
   
      return this.tas;
   }

   public Room withTas(TeachingAssistant... value)
   {
      if(value==null){
         return this;
      }
      for (TeachingAssistant item : value)
      {
         if (item != null)
         {
            if (this.tas == null)
            {
               this.tas = new TeachingAssistantSet();
            }
            
            boolean changed = this.tas.add (item);

            if (changed)
            {
               item.withRoom(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TAS, null, item);
            }
         }
      }
      return this;
   } 

   public Room withoutTas(TeachingAssistant... value)
   {
      for (TeachingAssistant item : value)
      {
         if ((this.tas != null) && (item != null))
         {
            if (this.tas.remove(item))
            {
               item.setRoom(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TAS, item, null);
            }
         }
      }
      return this;
   }

   public TeachingAssistant createTas()
   {
      TeachingAssistant value = new TeachingAssistant();
      withTas(value);
      return value;
   } 
}
