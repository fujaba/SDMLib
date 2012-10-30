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

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import org.sdmlib.model.taskflows.creators.LoggerSet;
import org.sdmlib.scenarios.CallDot;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class Logger extends TaskFlow implements PropertyChangeInterface
{
	@Override
	public void run() 
	{
		LogEntry previousEntry = null;
		
		if (!getEntries().isEmpty())
		{
			Iterator<LogEntry> entryIt = getEntries().iterator();
			while(entryIt.hasNext())
			{
					previousEntry = entryIt.next();
			}
		}
		
		LogEntry newEntry = createEntries()
		.withNodeName(targetTaskFlow.getIdMap().getSessionId())
		.withTaskName(targetTaskFlow.getTaskNames()[targetTaskFlow.getTaskNo()].toString());
		
		if(previousEntry != null)
		{
			newEntry.withParent(previousEntry);
		}
		
		boolean lastAction = targetTaskFlow.getTaskNo() + 1 >= targetTaskFlow.getTaskNames().length;
		
		targetTaskFlow.run();
		
		if (lastAction)
		{
			JsonArray jsonArray = targetTaskFlow.getIdMap().toJsonArray(this);
			
			//dump json file
			ScenarioManager.printFile(new File("doc/Logger.json"), jsonArray.toString(3));
			
			dumpDiagram();
		}
	}

	private void dumpDiagram() 
	{
		// 
		StringBuilder text = new StringBuilder(
                " digraph TaskFlowDiagram {\n" + 
                "    \n" + 
                "swimlanes \n" +
                "    \n"  +
                "links\n"  +
                "}");
                    
		
		StringBuilder swimlanes = dumpSwimlanes();
		
		StringBuilder linksText = new StringBuilder();
		
		for (LogEntry entry : this.getEntries())
		{
			LogEntry previousEntry = entry.getParent();
			if (previousEntry != null)
			{
				linksText.append("    " + previousEntry.getNodeName() + "_" + previousEntry.getTaskName() + " -> " + entry.getNodeName() + "_" + entry.getTaskName() + "; \n");
			}
		}
		
		CGUtil.replaceAll(text,  
				"swimlanes", swimlanes.toString(),
				"links", linksText.toString());
		
		// ScenarioManager.printFile(new File("doc/Logger.dot"), text.toString());
		
		CallDot.callDot(targetTaskFlow.getClass().getSimpleName(), text.toString());
	}

	private StringBuilder dumpSwimlanes() {
		StringBuilder swimlanes = new StringBuilder();
		
		LinkedHashSet<String> nodeNames = new LinkedHashSet<String>(this.getEntries().getNodeName());
		for(String nodeName : nodeNames)
		{
			// add one lane
			StringBuilder laneText = new StringBuilder(
					"    subgraph clusterLaneName {\n" +
					"    	rankdir=\"LR\";\n" + 
					"    	style=filled;\n" + 
					"		color=lightgrey;\n" + 
					"innerTasks\n" + 
					"    	label = \"LaneName\";\n" + 
					"    }\n"
					);

			StringBuilder innerTasksText = new StringBuilder();
			
			LinkedHashSet<String> taskNames = new LinkedHashSet<String>();
			for (LogEntry entry : this.getEntries())
			{
				if (nodeName.equals(entry.getNodeName()))
				{
					taskNames.add(entry.getTaskName());
				}
			}
			
			for (String taskName : taskNames)
			{
				innerTasksText.append("       " + nodeName+ "_" + taskName + ";\n");
			}
			
			CGUtil.replaceAll(laneText, 
					"LaneName", nodeName, 
					"innerTasks", innerTasksText.toString());
		
			swimlanes.append(laneText.toString());
		}
		return swimlanes;
	}

	public Object[] getTaskNames()
	{
		return null;
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

      if (PROPERTY_CHILDREN.equalsIgnoreCase(attrName))
      {
         addToChildren((Logger) value);
         return true;
      }
      
      if ((PROPERTY_CHILDREN + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromChildren((Logger) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((Logger) value);
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
      removeAllFromChildren();
      setParent(null);
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
   

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Logger ----------------------------------- Logger
    *              parent                   children
    * </pre>
    */
   
   public static final String PROPERTY_CHILDREN = "children";
   
   private LoggerSet children = null;
   
   public LoggerSet getChildren()
   {
      if (this.children == null)
      {
         return Logger.EMPTY_SET;
      }
   
      return this.children;
   }
   
   public boolean addToChildren(Logger value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.children == null)
         {
            this.children = new LoggerSet();
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
   
   public boolean removeFromChildren(Logger value)
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
   
   public Logger withChildren(Logger value)
   {
      addToChildren(value);
      return this;
   } 
   
   public Logger withoutChildren(Logger value)
   {
      removeFromChildren(value);
      return this;
   } 
   
   public void removeAllFromChildren()
   {
      LinkedHashSet<Logger> tmpSet = new LinkedHashSet<Logger>(this.getChildren());
   
      for (Logger value : tmpSet)
      {
         this.removeFromChildren(value);
      }
   }
   
   public Logger createChildren()
   {
      Logger value = new Logger();
      withChildren(value);
      return value;
   } 

   
   public static final LoggerSet EMPTY_SET = new LoggerSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Logger ----------------------------------- Logger
    *              children                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";
   
   private Logger parent = null;
   
   public Logger getParent()
   {
      return this.parent;
   }
   
   public boolean setParent(Logger value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         Logger oldValue = this.parent;
         
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
   
   public Logger withParent(Logger value)
   {
      setParent(value);
      return this;
   } 
   
   public Logger createParent()
   {
      Logger value = new Logger();
      withParent(value);
      return value;
   } 
}