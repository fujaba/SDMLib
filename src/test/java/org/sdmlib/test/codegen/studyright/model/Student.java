/*
   Copyright (c) 2018 zuend
   
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
   
package org.sdmlib.test.codegen.studyright.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;

public  class Student implements SendableEntity
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
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(listener);
   	}
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
      setUniversity(null);
      firePropertyChange("REMOVE_YOU", this, null);
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
      if (this.assignmentPoints != value) {
      
         int oldValue = this.assignmentPoints;
         this.assignmentPoints = value;
         this.firePropertyChange(PROPERTY_ASSIGNMENTPOINTS, oldValue, value);
      }
   }
   
   public Student withAssignmentPoints(int value)
   {
      setAssignmentPoints(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getAssignmentPoints());
      result.append(" ").append(this.getCredits());
      result.append(" ").append(this.getId());
      result.append(" ").append(this.getMotivation());
      result.append(" ").append(this.getName());
      return result.substring(1);
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
      if (this.credits != value) {
      
         int oldValue = this.credits;
         this.credits = value;
         this.firePropertyChange(PROPERTY_CREDITS, oldValue, value);
      }
   }
   
   public Student withCredits(int value)
   {
      setCredits(value);
      return this;
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
      if ( ! EntityUtil.stringEquals(this.id, value)) {
      
         String oldValue = this.id;
         this.id = value;
         this.firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public Student withId(String value)
   {
      setId(value);
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
      if (this.motivation != value) {
      
         int oldValue = this.motivation;
         this.motivation = value;
         this.firePropertyChange(PROPERTY_MOTIVATION, oldValue, value);
      }
   }
   
   public Student withMotivation(int value)
   {
      setMotivation(value);
      return this;
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
   
   public Student withName(String value)
   {
      setName(value);
      return this;
   } 

   
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
         
         firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
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
}
