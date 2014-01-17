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
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Student implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         return getId();
      }

      if (PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attrName))
      {
         return getAssignmentPoints();
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         return getMotivation();
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return getCredits();
      }

      if (PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         return getUniversity();
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return getIn();
      }

      if (PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         return getDone();
      }

      if (PROPERTY_FRIENDS.equalsIgnoreCase(attrName))
      {
         return getFriends();
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

      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         setId((String) value);
         return true;
      }

      if (PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attrName))
      {
         setAssignmentPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         setMotivation(Integer.parseInt(value.toString()));
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

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         setIn((Room) value);
         return true;
      }

      if (PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         addToDone((Assignment) value);
         return true;
      }
      
      if ((PROPERTY_DONE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromDone((Assignment) value);
         return true;
      }

      if (PROPERTY_FRIENDS.equalsIgnoreCase(attrName))
      {
         addToFriends((Student) value);
         return true;
      }
      
      if ((PROPERTY_FRIENDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromFriends((Student) value);
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
      setIn(null);
      removeAllFromDone();
      removeAllFromFriends();
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
   
   public Student withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getId());
      _.append(" ").append(this.getAssignmentPoints());
      _.append(" ").append(this.getMotivation());
      _.append(" ").append(this.getCredits());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_ID = "id";
   
   private String id;

   public String getId()
   {
      return this.id;
   }
   
   public void setId(String value)
   {
      if ( ! StrUtil.stringEquals(this.id, value))
      {
         String oldValue = this.id;
         this.id = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public Student withId(String value)
   {
      setId(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ASSIGNMENTPOINTS = "assignmentPoints";
   
   private int assignmentPoints;

   public int getAssignmentPoints()
   {
      return this.assignmentPoints;
   }
   
   public void setAssignmentPoints(int value)
   {
      if (this.assignmentPoints != value)
      {
         int oldValue = this.assignmentPoints;
         this.assignmentPoints = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENTPOINTS, oldValue, value);
      }
   }
   
   public Student withAssignmentPoints(int value)
   {
      setAssignmentPoints(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MOTIVATION = "motivation";
   
   private int motivation;

   public int getMotivation()
   {
      return this.motivation;
   }
   
   public void setMotivation(int value)
   {
      if (this.motivation != value)
      {
         int oldValue = this.motivation;
         this.motivation = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MOTIVATION, oldValue, value);
      }
   }
   
   public Student withMotivation(int value)
   {
      setMotivation(value);
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
   
   public Student withCredits(int value)
   {
      setCredits(value);
      return this;
   } 

   
   public static final StudentSet EMPTY_SET = new StudentSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Student ----------------------------------- University
    *              students                   university
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
            oldValue.withoutStudents(this);
         }
         
         this.university = value;
         
         if (value != null)
         {
            value.withStudents(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Student withUniversity(University value)
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
    *              many                       one
    * Student ----------------------------------- Room
    *              students                   in
    * </pre>
    */
   
   public static final String PROPERTY_IN = "in";
   
   private Room in = null;
   
   public Room getIn()
   {
      return this.in;
   }
   
   public boolean setIn(Room value)
   {
      boolean changed = false;
      
      if (this.in != value)
      {
         Room oldValue = this.in;
         
         if (this.in != null)
         {
            this.in = null;
            oldValue.withoutStudents(this);
         }
         
         this.in = value;
         
         if (value != null)
         {
            value.withStudents(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Student withIn(Room value)
   {
      setIn(value);
      return this;
   } 
   
   public Room createIn()
   {
      Room value = new Room();
      withIn(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Student ----------------------------------- Assignment
    *              students                   done
    * </pre>
    */
   
   public static final String PROPERTY_DONE = "done";
   
   private AssignmentSet done = null;
   
   public AssignmentSet getDone()
   {
      if (this.done == null)
      {
         return Assignment.EMPTY_SET;
      }
   
      return this.done;
   }
   
   public boolean addToDone(Assignment value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.done == null)
         {
            this.done = new AssignmentSet();
         }
         
         changed = this.done.add (value);
         
         if (changed)
         {
            value.withStudents(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromDone(Assignment value)
   {
      boolean changed = false;
      
      if ((this.done != null) && (value != null))
      {
         changed = this.done.remove (value);
         
         if (changed)
         {
            value.withoutStudents(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, value, null);
         }
      }
         
      return changed;   
   }
   
   public Student withDone(Assignment... value)
   {
      for (Assignment item : value)
      {
         addToDone(item);
      }
      return this;
   } 
   
   public Student withoutDone(Assignment... value)
   {
      for (Assignment item : value)
      {
         removeFromDone(item);
      }
      return this;
   }
   
   public void removeAllFromDone()
   {
      LinkedHashSet<Assignment> tmpSet = new LinkedHashSet<Assignment>(this.getDone());
   
      for (Assignment value : tmpSet)
      {
         this.removeFromDone(value);
      }
   }
   
   public Assignment createDone()
   {
      Assignment value = new Assignment();
      withDone(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Student ----------------------------------- Student
    *              friends                   friends
    * </pre>
    */
   
   public static final String PROPERTY_FRIENDS = "friends";
   
   private StudentSet friends = null;
   
   public StudentSet getFriends()
   {
      if (this.friends == null)
      {
         return Student.EMPTY_SET;
      }
   
      return this.friends;
   }
   
   public boolean addToFriends(Student value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.friends == null)
         {
            this.friends = new StudentSet();
         }
         
         changed = this.friends.add (value);
         
         if (changed)
         {
            value.withFriends(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_FRIENDS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromFriends(Student value)
   {
      boolean changed = false;
      
      if ((this.friends != null) && (value != null))
      {
         changed = this.friends.remove (value);
         
         if (changed)
         {
            value.withoutFriends(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_FRIENDS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Student withFriends(Student... value)
   {
      for (Student item : value)
      {
         addToFriends(item);
      }
      return this;
   } 
   
   public Student withoutFriends(Student... value)
   {
      for (Student item : value)
      {
         removeFromFriends(item);
      }
      return this;
   }
   
   public void removeAllFromFriends()
   {
      LinkedHashSet<Student> tmpSet = new LinkedHashSet<Student>(this.getFriends());
   
      for (Student value : tmpSet)
      {
         this.removeFromFriends(value);
      }
   }
   
   public Student createFriends()
   {
      Student value = new Student();
      withFriends(value);
      return value;
   } 
}

