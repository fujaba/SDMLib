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
   
package org.sdmlib.examples.studyright.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.examples.studyright.model.util.LectureSet;
import org.sdmlib.examples.studyright.model.util.RoomSet;
import org.sdmlib.examples.studyright.model.util.StudentSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class Room implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public int studentCount(  )
   {
      return 0;
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
      withoutNeighbors(this.getNeighbors().toArray(new Room[this.getNeighbors().size()]));
      withoutLecture(this.getLecture().toArray(new Lecture[this.getLecture().size()]));
      setUni(null);
      withoutStudents(this.getStudents().toArray(new Student[this.getStudents().size()]));
      setRoom(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ROOMNO = "roomNo";
   
   private String roomNo;

   public String getRoomNo()
   {
      return this.roomNo;
   }
   
   public void setRoomNo(String value)
   {
      if ( ! StrUtil.stringEquals(this.roomNo, value))
      {
         String oldValue = this.roomNo;
         this.roomNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMNO, oldValue, value);
      }
   }
   
   public Room withRoomNo(String value)
   {
      setRoomNo(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getRoomNo());
      s.append(" ").append(this.getCredits());
      return s.substring(1);
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
    *              many                       many
    * Room ----------------------------------- Room
    *              neighbors                   neighbors
    * </pre>
    */
   
   public static final String PROPERTY_NEIGHBORS = "neighbors";

   private RoomSet neighbors = null;
   
   public RoomSet getNeighbors()
   {
      if (this.neighbors == null)
      {
         return Room.EMPTY_SET;
      }
   
      return this.neighbors;
   }
   public RoomSet getNeighborsTransitive()
   {
      RoomSet result = new RoomSet().with(this);
      return result.getNeighborsTransitive();
   }


   public boolean addToNeighbors(Room value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.neighbors == null)
         {
            this.neighbors = new RoomSet();
         }
         
         changed = this.neighbors.add (value);
         
         if (changed)
         {
            value.withNeighbors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEIGHBORS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromNeighbors(Room value)
   {
      boolean changed = false;
      
      if ((this.neighbors != null) && (value != null))
      {
         changed = this.neighbors.remove(value);
         
         if (changed)
         {
            value.withoutNeighbors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEIGHBORS, value, null);
         }
      }
         
      return changed;   
   }

   public Room withNeighbors(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         addToNeighbors(item);
      }
      return this;
   } 

   public Room withoutNeighbors(Room... value)
   {
      for (Room item : value)
      {
         removeFromNeighbors(item);
      }
      return this;
   }

   public void removeAllFromNeighbors()
   {
      LinkedHashSet<Room> tmpSet = new LinkedHashSet<Room>(this.getNeighbors());
   
      for (Room value : tmpSet)
      {
         this.removeFromNeighbors(value);
      }
   }

   public Room createNeighbors()
   {
      Room value = new Room();
      withNeighbors(value);
      return value;
   } 

   
   public static final RoomSet EMPTY_SET = new RoomSet();

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Lecture
    *              in                   lecture
    * </pre>
    */
   
   public static final String PROPERTY_LECTURE = "lecture";

   private LectureSet lecture = null;
   
   public LectureSet getLecture()
   {
      if (this.lecture == null)
      {
         return Lecture.EMPTY_SET;
      }
   
      return this.lecture;
   }

   public boolean addToLecture(Lecture value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.lecture == null)
         {
            this.lecture = new LectureSet();
         }
         
         changed = this.lecture.add (value);
         
         if (changed)
         {
            value.withIn(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromLecture(Lecture value)
   {
      boolean changed = false;
      
      if ((this.lecture != null) && (value != null))
      {
         changed = this.lecture.remove(value);
         
         if (changed)
         {
            value.setIn(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, value, null);
         }
      }
         
      return changed;   
   }

   public Room withLecture(Lecture... value)
   {
      if(value==null){
         return this;
      }
      for (Lecture item : value)
      {
         addToLecture(item);
      }
      return this;
   } 

   public Room withoutLecture(Lecture... value)
   {
      for (Lecture item : value)
      {
         removeFromLecture(item);
      }
      return this;
   }

   public void removeAllFromLecture()
   {
      LinkedHashSet<Lecture> tmpSet = new LinkedHashSet<Lecture>(this.getLecture());
   
      for (Lecture value : tmpSet)
      {
         this.removeFromLecture(value);
      }
   }

   public Lecture createLecture()
   {
      Lecture value = new Lecture();
      withLecture(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Room ----------------------------------- University
    *              rooms                   uni
    * </pre>
    */
   
   public static final String PROPERTY_UNI = "uni";

   private University uni = null;

   public University getUni()
   {
      return this.uni;
   }

   public boolean setUni(University value)
   {
      boolean changed = false;
      
      if (this.uni != value)
      {
         University oldValue = this.uni;
         
         if (this.uni != null)
         {
            this.uni = null;
            oldValue.withoutRooms(this);
         }
         
         this.uni = value;
         
         if (value != null)
         {
            value.withRooms(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UNI, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withUni(University value)
   {
      setUni(value);
      return this;
   } 

   public University createUni()
   {
      University value = new University();
      withUni(value);
      return value;
   } 

   
   //==========================================================================
   
   public void findPath( String p0, int p1 )
   {
      
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
         changed = this.students.remove(value);
         
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
      if(value==null){
         return this;
      }
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
    *              many                       one
    * Room ----------------------------------- Assignment
    *              assignments                   room
    * </pre>
    */
   
   public static final String PROPERTY_ROOM = "room";

   private Assignment room = null;

   public Assignment getRoom()
   {
      return this.room;
   }

   public boolean setRoom(Assignment value)
   {
      boolean changed = false;
      
      if (this.room != value)
      {
         Assignment oldValue = this.room;
         
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

   public Room withRoom(Assignment value)
   {
      setRoom(value);
      return this;
   } 

   public Assignment createRoom()
   {
      Assignment value = new Assignment();
      withRoom(value);
      return value;
   } 
}

