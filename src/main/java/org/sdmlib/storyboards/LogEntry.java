/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.storyboards;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.EntityUtil;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/mikado/MikadoMethodModel.java'>MikadoMethodModel.java</a>
 */
   public  class LogEntry implements SendableEntity
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
      setGoal(null);
      setParent(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DATE = "date";
   
   private String date;

   public String getDate()
   {
      return this.date;
   }
   
   public void setDate(String value)
   {
      if ( ! EntityUtil.stringEquals(this.date, value)) {
      
         String oldValue = this.date;
         this.date = value;
         this.firePropertyChange(PROPERTY_DATE, oldValue, value);
      }
   }
   
   public LogEntry withDate(String value)
   {
      setDate(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getDate());
      result.append(" ").append(this.getHoursDone());
      result.append(" ").append(this.getHoursRemaining());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_HOURSDONE = "hoursDone";
   
   private double hoursDone;

   public double getHoursDone()
   {
      return this.hoursDone;
   }
   
   public void setHoursDone(double value)
   {
      if (this.hoursDone != value) {
      
         double oldValue = this.hoursDone;
         this.hoursDone = value;
         this.firePropertyChange(PROPERTY_HOURSDONE, oldValue, value);
      }
   }

   public LogEntry withHoursDone(double value)
   {
      setHoursDone(value);
      return this;
   }

   public LogEntry incrementHoursDone(double value)
   {
      double tmp = this.getHoursDone();
      setHoursDone(tmp + value);
      return this;
   }



   
   //==========================================================================
   
   public static final String PROPERTY_HOURSREMAINING = "hoursRemaining";
   
   private double hoursRemaining;

   public double getHoursRemaining()
   {
      return this.hoursRemaining;
   }
   
   public void setHoursRemaining(double value)
   {
      if (this.hoursRemaining != value) {
      
         double oldValue = this.hoursRemaining;
         this.hoursRemaining = value;
         this.firePropertyChange(PROPERTY_HOURSREMAINING, oldValue, value);
      }
   }
   
   public LogEntry withHoursRemaining(double value)
   {
      setHoursRemaining(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * LogEntry ----------------------------------- Goal
    *              logentry                   goal
    * </pre>
    */
   
   public static final String PROPERTY_GOAL = "goal";

   private Goal goal = null;

   public Goal getGoal()
   {
      return this.goal;
   }

   public boolean setGoal(Goal value)
   {
      boolean changed = false;
      
      if (this.goal != value)
      {
         Goal oldValue = this.goal;
         
         this.goal = value;
         
         firePropertyChange(PROPERTY_GOAL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LogEntry withGoal(Goal value)
   {
      setGoal(value);
      return this;
   } 

   public Goal createGoal()
   {
      Goal value = new Goal();
      withGoal(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntry ----------------------------------- MikadoLog
    *              entries                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private MikadoLog parent = null;

   public MikadoLog getParent()
   {
      return this.parent;
   }

   public boolean setParent(MikadoLog value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         MikadoLog oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutEntries(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withEntries(this);
         }
         
         firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LogEntry withParent(MikadoLog value)
   {
      setParent(value);
      return this;
   } 

   public MikadoLog createParent()
   {
      MikadoLog value = new MikadoLog();
      withParent(value);
      return value;
   } 
}
