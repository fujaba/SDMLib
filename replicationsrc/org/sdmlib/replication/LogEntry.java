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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sdmlib.utils.StrUtil;
import org.sdmlib.replication.creators.LogEntrySet;
import java.beans.PropertyChangeListener;

public class LogEntry implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_STEPNAME.equalsIgnoreCase(attrName))
      {
         return getStepName();
      }

      if (PROPERTY_EXECUTEDBY.equalsIgnoreCase(attrName))
      {
         return getExecutedBy();
      }

      if (PROPERTY_TIMESTAMP.equalsIgnoreCase(attrName))
      {
         return getTimeStamp();
      }

      if (PROPERTY_TASK.equalsIgnoreCase(attrName))
      {
         return getTask();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STEPNAME.equalsIgnoreCase(attrName))
      {
         setStepName((String) value);
         return true;
      }

      if (PROPERTY_EXECUTEDBY.equalsIgnoreCase(attrName))
      {
         setExecutedBy((String) value);
         return true;
      }

      if (PROPERTY_TIMESTAMP.equalsIgnoreCase(attrName))
      {
         setTimeStamp(Long.parseLong(value.toString()));
         return true;
      }

      if (PROPERTY_TASK.equalsIgnoreCase(attrName))
      {
         setTask((Task) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      setTask(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_STEPNAME = "stepName";

   private String stepName;

   public String getStepName()
   {
      return this.stepName;
   }

   public void setStepName(String value)
   {
      if (!StrUtil.stringEquals(this.stepName, value))
      {
         String oldValue = this.stepName;
         this.stepName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STEPNAME,
            oldValue, value);
      }
   }

   public LogEntry withStepName(String value)
   {
      setStepName(value);
      return this;
   }

   private SimpleDateFormat formater = new SimpleDateFormat("H:mm:ss.SSS");

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(formater.format(new Date(this.getTimeStamp())));
      _.append(" ").append(this.getStepName());
      _.append(" ").append(this.getExecutedBy());
      return _.substring(1);
   }

   // ==========================================================================

   public static final String PROPERTY_EXECUTEDBY = "executedBy";

   private String executedBy;

   public String getExecutedBy()
   {
      return this.executedBy;
   }

   public void setExecutedBy(String value)
   {
      if (!StrUtil.stringEquals(this.executedBy, value))
      {
         String oldValue = this.executedBy;
         this.executedBy = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EXECUTEDBY,
            oldValue, value);
      }
   }

   public LogEntry withExecutedBy(String value)
   {
      setExecutedBy(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_TIMESTAMP = "timeStamp";

   private long timeStamp;

   public long getTimeStamp()
   {
      return this.timeStamp;
   }

   public void setTimeStamp(long value)
   {
      if (this.timeStamp != value)
      {
         long oldValue = this.timeStamp;
         this.timeStamp = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TIMESTAMP,
            oldValue, value);
      }
   }

   public LogEntry withTimeStamp(long value)
   {
      setTimeStamp(value);
      return this;
   }

   public static final LogEntrySet EMPTY_SET = new LogEntrySet();

   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntry ----------------------------------- Task
    *              logEntries                   task
    * </pre>
    */

   public static final String PROPERTY_TASK = "task";

   private Task task = null;

   public Task getTask()
   {
      return this.task;
   }

   public boolean setTask(Task value)
   {
      boolean changed = false;

      if (this.task != value)
      {
         Task oldValue = this.task;

         if (this.task != null)
         {
            this.task = null;
            oldValue.withoutLogEntries(this);
         }

         this.task = value;

         if (value != null)
         {
            value.withLogEntries(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASK, oldValue,
            value);
         changed = true;
      }

      return changed;
   }

   public LogEntry withTask(Task value)
   {
      setTask(value);
      return this;
   }

   public Task createTask()
   {
      Task value = new Task();
      withTask(value);
      return value;
   }
}

