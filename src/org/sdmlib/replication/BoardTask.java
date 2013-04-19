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
   
package org.sdmlib.replication;

import org.sdmlib.replication.Task;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.creators.BoardTaskSet;

public class BoardTask extends Task implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return getLogEntries();
      }

      if (PROPERTY_CURRENTSTEP.equalsIgnoreCase(attrName))
      {
         return getCurrentStep();
      }

      if (PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         return getLane();
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

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         addToLogEntries((LogEntry) value);
         return true;
      }
      
      if ((PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLogEntries((LogEntry) value);
         return true;
      }

      if (PROPERTY_CURRENTSTEP.equalsIgnoreCase(attrName))
      {
         setCurrentStep((Step) value);
         return true;
      }

      if (PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         setLane((Lane) value);
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

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromLogEntries();
      setCurrentStep(null);
      setLane(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
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
   
   public BoardTask withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   public static final BoardTaskSet EMPTY_SET = new BoardTaskSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * BoardTask ----------------------------------- Step
    *              tasks                   currentStep
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTSTEP = "currentStep";
   
   private Step currentStep = null;
   
   public Step getCurrentStep()
   {
      return this.currentStep;
   }
   
   public boolean setCurrentStep(Step value)
   {
      boolean changed = false;
      
      if (this.currentStep != value)
      {
         Step oldValue = this.currentStep;
         
         if (this.currentStep != null)
         {
            this.currentStep = null;
            oldValue.withoutTasks(this);
         }
         
         this.currentStep = value;
         
         if (value != null)
         {
            value.withTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTSTEP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public BoardTask withCurrentStep(Step value)
   {
      setCurrentStep(value);
      return this;
   } 
   
   public Step createCurrentStep()
   {
      Step value = new Step();
      withCurrentStep(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * BoardTask ----------------------------------- Lane
    *              tasks                   lane
    * </pre>
    */
   
   public static final String PROPERTY_LANE = "lane";
   
   private Lane lane = null;
   
   public Lane getLane()
   {
      return this.lane;
   }
   
   public boolean setLane(Lane value)
   {
      boolean changed = false;
      
      if (this.lane != value)
      {
         Lane oldValue = this.lane;
         
         if (this.lane != null)
         {
            this.lane = null;
            oldValue.withoutTasks(this);
         }
         
         this.lane = value;
         
         if (value != null)
         {
            value.withTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public BoardTask withLane(Lane value)
   {
      setLane(value);
      return this;
   } 
   
   public Lane createLane()
   {
      Lane value = new Lane();
      withLane(value);
      return value;
   } 
}

