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
import org.sdmlib.examples.studyright.model.util.AssignmentSet;
import org.sdmlib.examples.studyright.model.util.LectureSet;
import org.sdmlib.examples.studyright.model.util.StudentSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class Student extends Female implements Male, PropertyChangeInterface
{

   
   //==========================================================================
   
   public void findMyPosition(  )
   {
      
   }

   
   //==========================================================================
   
   public void findMyPosition( String p0 )
   {
      
   }

   
   //==========================================================================
   
   public void findMyPosition( String p0, int p1 )
   {
      
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();
      withoutLecture(this.getLecture().toArray(new Lecture[this.getLecture().size()]));
      setUni(null);
      setIn(null);
      withoutDone(this.getDone().toArray(new Assignment[this.getDone().size()]));
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
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getMatrNo());
      _.append(" ").append(this.getCredits());
      _.append(" ").append(this.getMotivation());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_MATRNO = "matrNo";
   
   private int matrNo;

   public int getMatrNo()
   {
      return this.matrNo;
   }
   
   public void setMatrNo(int value)
   {
      if (this.matrNo != value)
      {
         int oldValue = this.matrNo;
         this.matrNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MATRNO, oldValue, value);
      }
   }
   
   public Student withMatrNo(int value)
   {
      setMatrNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Student ----------------------------------- Lecture
    *              listen                   lecture
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
            value.withListen(this);
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
            value.setListen(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, value, null);
         }
      }
         
      return changed;   
   }

   public Student withLecture(Lecture... value)
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

   public Student withoutLecture(Lecture... value)
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

   
   public static final StudentSet EMPTY_SET = new StudentSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Student ----------------------------------- University
    *              students                   uni
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
            oldValue.withoutStudents(this);
         }
         
         this.uni = value;
         
         if (value != null)
         {
            value.withStudents(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UNI, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Student withUni(University value)
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
    *              one                       many
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
         changed = this.done.remove(value);
         
         if (changed)
         {
            value.setStudents(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, value, null);
         }
      }
         
      return changed;   
   }

   public Student withDone(Assignment... value)
   {
      if(value==null){
         return this;
      }
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
}
