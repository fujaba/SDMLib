/*
   Copyright (c) 2012 deeptought 
   
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
   
package org.sdmlib.models.taskflows;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.models.taskflows.util.LogEntrySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.taskflows.Logger;
   /**
    * 
    * @see <a href='../../../../../../../src/main/replication/org/sdmlib/models/taskflows/TaskFlowModel.java'>TaskFlowModel.java</a>
* @see <a href='../../../../../../../src/test/java/org/sdmlib/test/models/taskflows/TaskFlowModel.java'>TaskFlowModel.java</a>
 * @see org.sdmlib.test.models.taskflows.TaskFlowModel#taskFlowModel
 */
   public class LogEntry implements PropertyChangeInterface, SendableEntity
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

      if (PROPERTY_NODENAME.equalsIgnoreCase(attribute))
      {
         return getNodeName();
      }

      if (PROPERTY_TASKNAME.equalsIgnoreCase(attribute))
      {
         return getTaskName();
      }

      if (PROPERTY_LOGGER.equalsIgnoreCase(attrName))
      {
         return getLogger();
      }

      if (PROPERTY_CHILDREN.equalsIgnoreCase(attrName))
      {
         return getChildren();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NODENAME.equalsIgnoreCase(attrName))
      {
         setNodeName((String) value);
         return true;
      }

      if (PROPERTY_TASKNAME.equalsIgnoreCase(attrName))
      {
         setTaskName((String) value);
         return true;
      }

      if (PROPERTY_LOGGER.equalsIgnoreCase(attrName))
      {
         setLogger((Logger) value);
         return true;
      }

      if (PROPERTY_CHILDREN.equalsIgnoreCase(attrName))
      {
         addToChildren((LogEntry) value);
         return true;
      }
      
      if ((PROPERTY_CHILDREN + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromChildren((LogEntry) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((LogEntry) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}


   //==========================================================================
   
   public void removeYou()
   {
      setLogger(null);
      removeAllFromChildren();
      setParent(null);
      withoutChildren(this.getChildren().toArray(new LogEntry[this.getChildren().size()]));
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
         return new LogEntrySet();
      }
   
      return this.children;
   }
   
   public boolean addToChildren(LogEntry value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.children == null)
         {
            this.children = new LogEntrySet();
         }
         
         changed = this.children.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHILDREN, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromChildren(LogEntry value)
   {
      boolean changed = false;
      
      if ((this.children != null) && (value != null))
      {
         changed = this.children.remove (value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHILDREN, value, null);
         }
      }
         
      return changed;   
   }
   
   public LogEntry withChildren(LogEntry value)
   {
      addToChildren(value);
      return this;
   } 
   
   public LogEntry withoutChildren(LogEntry value)
   {
      removeFromChildren(value);
      return this;
   } 
   
   public void removeAllFromChildren()
   {
      LinkedHashSet<LogEntry> tmpSet = new LinkedHashSet<LogEntry>(this.getChildren());
   
      for (LogEntry value : tmpSet)
      {
         this.removeFromChildren(value);
      }
   }
   
	/**
	 *  Create Children LogEntry
	 *  @return the created Children
	 * @see <a href='../../../../../../../src/main/replication/org/sdmlib/models/taskflows/TaskFlowObjectScenarioForCoverage.java'>TaskFlowObjectScenarioForCoverage.java</a>
	 */
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


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getNodeName());
      s.append(" ").append(this.getTaskName());
      return s.substring(1);
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
         
      }
      return this;
   }
   public LogEntrySet getParentTransitive()
   {
      LogEntrySet result = new LogEntrySet().with(this);
      return result.getParentTransitive();
   }


   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }

