/*
   Copyright (c) 2012 zuendorf 
   
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

import java.beans.PropertyChangeSupport;

import org.sdmlib.examples.studyright.creators.AssignmentSet;
import org.sdmlib.examples.studyright.creators.StudentSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

import java.util.LinkedHashSet;

import org.sdmlib.serialization.json.JsonIdMap;
import java.beans.PropertyChangeListener;

public class Student implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         return getMatrNo();
      }

      if (PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         return getUni();
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return getIn();
      }

      if (PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         return getDone();
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return getCredits();
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         return getMotivation();
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

      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         setMatrNo((Integer) value);
         return true;
      }

      if (PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         setUni((University) value);
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

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         setMotivation(Integer.parseInt(value.toString()));
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setUni(null);
      setIn(null);
      removeAllFromDone();
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getMatrNo());
      _.append(" ").append(this.getCredits());
      _.append(" ").append(this.getMotivation());
      return _.substring(1);
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
         changed = this.done.remove (value);
         
         if (changed)
         {
            value.setStudents(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_DONE, value, null);
         }
      }
         
      return changed;   
   }
   
   public Student withDone(Assignment value)
   {
      addToDone(value);
      return this;
   } 
   
   public Student withoutDone(Assignment value)
   {
      removeFromDone(value);
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
}

