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
   
package org.sdmlib.model.taskflows;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.model.taskflows.util.LogEntrySet;

public class LogEntry implements PropertyChangeInterface
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
      setLogger(null);
      withoutChildren(this.getChildren().toArray(new LogEntry[this.getChildren().size()]));
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NODENAME = "nodeName";
   
   private String nodeName;

   public String getNodeName()
   {
      return this.nodeName;
   }
   
   public void setNodeName(String value)
   {
      if ( ! StrUtil.stringEquals(this.nodeName, value))
      {
         String oldValue = this.nodeName;
         this.nodeName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NODENAME, oldValue, value);
      }
   }
   
   public LogEntry withNodeName(String value)
   {
      setNodeName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getNodeName());
      _.append(" ").append(this.getTaskName());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TASKNAME = "taskName";
   
   private String taskName;

   public String getTaskName()
   {
      return this.taskName;
   }
   
   public void setTaskName(String value)
   {
      if ( ! StrUtil.stringEquals(this.taskName, value))
      {
         String oldValue = this.taskName;
         this.taskName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKNAME, oldValue, value);
      }
   }
   
   public LogEntry withTaskName(String value)
   {
      setTaskName(value);
      return this;
   } 

   
   public static final LogEntrySet EMPTY_SET = new LogEntrySet().withReadonly(true);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntry ----------------------------------- Logger
    *              entries                   logger
    * </pre>
    */
   
   public static final String PROPERTY_LOGGER = "logger";

   private Logger logger = null;

   public Logger getLogger()
   {
      return this.logger;
   }

   public boolean setLogger(Logger value)
   {
      boolean changed = false;
      
      if (this.logger != value)
      {
         Logger oldValue = this.logger;
         
         if (this.logger != null)
         {
            this.logger = null;
            oldValue.withoutEntries(this);
         }
         
         this.logger = value;
         
         if (value != null)
         {
            value.withEntries(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGGER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LogEntry withLogger(Logger value)
   {
      setLogger(value);
      return this;
   } 

   public Logger createLogger()
   {
      Logger value = new Logger();
      withLogger(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * LogEntry ----------------------------------- LogEntry
    *              parent                   children
    * </pre>
    */
   
   public static final String PROPERTY_CHILDREN = "children";

   private LogEntrySet children = null;
   
   public LogEntrySet getChildren()
   {
      if (this.children == null)
      {
         return LogEntry.EMPTY_SET;
      }
   
      return this.children;
   }
   public LogEntrySet getChildrenTransitive()
   {
      LogEntrySet result = new LogEntrySet().with(this);
      return result.getChildrenTransitive();
   }


   public LogEntry withChildren(LogEntry... value)
   {
      if(value==null){
         return this;
      }
      for (LogEntry item : value)
      {
         if (item != null)
         {
            if (this.children == null)
            {
               this.children = new LogEntrySet();
            }
            
            boolean changed = this.children.add (item);

            if (changed)
            {
               item.withParent(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHILDREN, null, item);
            }
         }
      }
      return this;
   } 

   public LogEntry withoutChildren(LogEntry... value)
   {
      for (LogEntry item : value)
      {
         if ((this.children != null) && (item != null))
         {
            if (this.children.remove(item))
            {
               item.setParent(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHILDREN, item, null);
            }
         }
         withoutChildren(item);
      }
      return this;
   }

   public LogEntry createChildren()
   {
      LogEntry value = new LogEntry();
      withChildren(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntry ----------------------------------- LogEntry
    *              children                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private LogEntry parent = null;

   public LogEntry getParent()
   {
      return this.parent;
   }
   public LogEntrySet getParentTransitive()
   {
      LogEntrySet result = new LogEntrySet().with(this);
      return result.getParentTransitive();
   }


   public boolean setParent(LogEntry value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         LogEntry oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutChildren(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withChildren(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LogEntry withParent(LogEntry value)
   {
      setParent(value);
      return this;
   } 

   public LogEntry createParent()
   {
      LogEntry value = new LogEntry();
      withParent(value);
      return value;
   } 
}
