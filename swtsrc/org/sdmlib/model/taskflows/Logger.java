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
   
package org.sdmlib.model.taskflows;

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Logger extends TaskFlow implements PropertyChangeInterface
{
	@Override
	public void run() 
	{
		// add yourself as listener if not yet done ?
		createEntries()
		.withNodeName(targetTaskFlow.getIdMap().getSessionId())
		.withTaskName(targetTaskFlow.getCurrentTaskName());
		
		targetTaskFlow.run();
	} 

	public String getCurrentTaskName()
	{
		return "DoLogging";
	}


   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_ENTRIES.equalsIgnoreCase(attrName))
      {
         return getEntries();
      }

      if (PROPERTY_TARGETTASKFLOW.equalsIgnoreCase(attrName))
      {
         return getTargetTaskFlow();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_ENTRIES.equalsIgnoreCase(attrName))
      {
         addToEntries((LogEntry) value);
         return true;
      }
      
      if ((PROPERTY_ENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromEntries((LogEntry) value);
         return true;
      }

      if (PROPERTY_TARGETTASKFLOW.equalsIgnoreCase(attrName))
      {
         setTargetTaskFlow((TaskFlow) value);
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
      removeAllFromEntries();
      setTargetTaskFlow(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Logger ----------------------------------- LogEntry
    *              logger                   entries
    * </pre>
    */
   
   public static final String PROPERTY_ENTRIES = "entries";
   
   private LogEntrySet entries = null;
   
   public LogEntrySet getEntries()
   {
      if (this.entries == null)
      {
         return LogEntry.EMPTY_SET;
      }
   
      return this.entries;
   }
   
   public boolean addToEntries(LogEntry value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.entries == null)
         {
            this.entries = new LogEntrySet();
         }
         
         changed = this.entries.add (value);
         
         if (changed)
         {
            value.withLogger(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ENTRIES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromEntries(LogEntry value)
   {
      boolean changed = false;
      
      if ((this.entries != null) && (value != null))
      {
         changed = this.entries.remove (value);
         
         if (changed)
         {
            value.setLogger(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ENTRIES, value, null);
         }
      }
         
      return changed;   
   }
   
   public Logger withEntries(LogEntry value)
   {
      addToEntries(value);
      return this;
   } 
   
   public Logger withoutEntries(LogEntry value)
   {
      removeFromEntries(value);
      return this;
   } 
   
   public void removeAllFromEntries()
   {
      LinkedHashSet<LogEntry> tmpSet = new LinkedHashSet<LogEntry>(this.getEntries());
   
      for (LogEntry value : tmpSet)
      {
         this.removeFromEntries(value);
      }
   }
   
   public LogEntry createEntries()
   {
      LogEntry value = new LogEntry();
      withEntries(value);
      return value;
   }



   
   /********************************************************************
    * <pre>
    *              one                       one
    * Logger ----------------------------------- TaskFlow
    *              logger                   targetTaskFlow
    * </pre>
    */
   
   public static final String PROPERTY_TARGETTASKFLOW = "targetTaskFlow";
   
   private TaskFlow targetTaskFlow = null;
   
   public TaskFlow getTargetTaskFlow()
   {
      return this.targetTaskFlow;
   }
   
   public boolean setTargetTaskFlow(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.targetTaskFlow != value)
      {
         TaskFlow oldValue = this.targetTaskFlow;
         
         if (this.targetTaskFlow != null)
         {
            this.targetTaskFlow = null;
            oldValue.setLogger(null);
         }
         
         this.targetTaskFlow = value;
         
         if (value != null)
         {
            value.withLogger(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETTASKFLOW, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Logger withTargetTaskFlow(TaskFlow value)
   {
      setTargetTaskFlow(value);
      return this;
   } 
   
}

