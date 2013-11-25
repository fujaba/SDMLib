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
   
package org.sdmlib.examples.studyrightWithAssignments;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;

public class Room implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_TOPIC.equalsIgnoreCase(attrName))
      {
         return getTopic();
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return getCredits();
      }

      if (PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         return getUniversity();
      }

      if (PROPERTY_DOORS.equalsIgnoreCase(attrName))
      {
         return getDoors();
      }

      if (PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return getStudents();
      }

      if (PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         return getAssignments();
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

      if (PROPERTY_TOPIC.equalsIgnoreCase(attrName))
      {
         setTopic((String) value);
         return true;
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         setUniversity((University) value);
         return true;
      }

      if (PROPERTY_DOORS.equalsIgnoreCase(attrName))
      {
         addToDoors((Room) value);
         return true;
      }
      
      if ((PROPERTY_DOORS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromDoors((Room) value);
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

      if (PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         addToAssignments((Assignment) value);
         return true;
      }
      
      if ((PROPERTY_ASSIGNMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromAssignments((Assignment) value);
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
      setUniversity(null);
      removeAllFromDoors();
      removeAllFromStudents();
      removeAllFromAssignments();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public void findPath( String p0, int p1 )
   {
      
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getTopic());
      _.append(" ").append(this.getCredits());
      return _.substring(1);
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

   
   public static final RoomSet EMPTY_SET = new RoomSet();

   
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
         return Room.EMPTY_SET;
      }
   
      return this.doors;
   }
   
   public boolean addToDoors(Room value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.doors == null)
         {
            this.doors = new RoomSet();
         }
         
         changed = this.doors.add (value);
         
         if (changed)
         {
            value.withDoors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DOORS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromDoors(Room value)
   {
      boolean changed = false;
      
      if ((this.doors != null) && (value != null))
      {
         changed = this.doors.remove (value);
         
         if (changed)
         {
            value.withoutDoors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DOORS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Room withDoors(Room... value)
   {
      for (Room item : value)
      {
         addToDoors(item);
      }
      return this;
   } 
   
   public Room withoutDoors(Room... value)
   {
      for (Room item : value)
      {
         removeFromDoors(item);
      }
      return this;
   }
   
   public void removeAllFromDoors()
   {
      LinkedHashSet<Room> tmpSet = new LinkedHashSet<Room>(this.getDoors());
   
      for (Room value : tmpSet)
      {
         this.removeFromDoors(value);
      }
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
            value.withIn(this);
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
            value.setIn(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STUDENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Room withStudents(Student... value)
   {
      for (Student item : value)
      {
         addToStudents(item);
      }
      return this;
   } 
   
   public Room withoutStudents(Student... value)
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
         return Assignment.EMPTY_SET;
      }
   
      return this.assignments;
   }
   
   public boolean addToAssignments(Assignment value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.assignments == null)
         {
            this.assignments = new AssignmentSet();
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
   
   public boolean removeFromAssignments(Assignment value)
   {
      boolean changed = false;
      
      if ((this.assignments != null) && (value != null))
      {
         changed = this.assignments.remove (value);
         
         if (changed)
         {
            value.setRoom(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Room withAssignments(Assignment... value)
   {
      for (Assignment item : value)
      {
         addToAssignments(item);
      }
      return this;
   } 
   
   public Room withoutAssignments(Assignment... value)
   {
      for (Assignment item : value)
      {
         removeFromAssignments(item);
      }
      return this;
   }
   
   public void removeAllFromAssignments()
   {
      LinkedHashSet<Assignment> tmpSet = new LinkedHashSet<Assignment>(this.getAssignments());
   
      for (Assignment value : tmpSet)
      {
         this.removeFromAssignments(value);
      }
   }
   
   public Assignment createAssignments()
   {
      Assignment value = new Assignment();
      withAssignments(value);
      return value;
   } 
}

