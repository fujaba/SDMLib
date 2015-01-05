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
   
package org.sdmlib.examples.studyrightWithAssignments.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class Student implements PropertyChangeInterface
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
      setUniversity(null);
      setIn(null);
      withoutDone(this.getDone().toArray(new Assignment[this.getDone().size()]));
      withoutFriends(this.getFriends().toArray(new Student[this.getFriends().size()]));
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


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getId());
      result.append(" ").append(this.getAssignmentPoints());
      result.append(" ").append(this.getMotivation());
      result.append(" ").append(this.getCredits());
      return result.substring(1);
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

   
   public static final StudentSet EMPTY_SET = new StudentSet().withReadonly(true);

   
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

   public Student withDone(Assignment... value)
   {
      if(value==null){
         return this;
      }
      for (Assignment item : value)
      {
         if (item != null)
         {
            if (this.done == null)
            {
               this.done = new AssignmentSet();
            }
            
            boolean changed = this.done.add (item);

            if (changed)
            {
               item.withStudents(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, null, item);
            }
         }
      }
      return this;
   } 

   public Student withoutDone(Assignment... value)
   {
      for (Assignment item : value)
      {
         if ((this.done != null) && (item != null))
         {
            if (this.done.remove(item))
            {
               item.withoutStudents(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, item, null);
            }
         }
      }
      return this;
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
   public StudentSet getFriendsTransitive()
   {
      StudentSet result = new StudentSet().with(this);
      return result.getFriendsTransitive();
   }


   public Student withFriends(Student... value)
   {
      if(value==null){
         return this;
      }
      for (Student item : value)
      {
         if (item != null)
         {
            if (this.friends == null)
            {
               this.friends = new StudentSet();
            }
            
            boolean changed = this.friends.add (item);

            if (changed)
            {
               item.withFriends(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FRIENDS, null, item);
            }
         }
      }
      return this;
   } 

   public Student withoutFriends(Student... value)
   {
      for (Student item : value)
      {
         if ((this.friends != null) && (item != null))
         {
            if (this.friends.remove(item))
            {
               item.withoutFriends(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FRIENDS, item, null);
            }
         }
      }
      return this;
   }

   public Student createFriends()
   {
      Student value = new Student();
      withFriends(value);
      return value;
   } 

   public TeachingAssistant createFriendsTeachingAssistant()
   {
      TeachingAssistant value = new TeachingAssistant();
      withFriends(value);
      return value;
   } 
}
