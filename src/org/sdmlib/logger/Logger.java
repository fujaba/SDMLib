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

package org.sdmlib.logger;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.logger.util.LogEntrySet;
import org.sdmlib.logger.util.LoggerSet;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.StoryboardManager;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class Logger extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames 
   {
      Start, Collect
   }

   public Object[] getTaskNames()
   {
      return TaskNames.values();
   }


   @Override
   public void run() 
   {
      switch (TaskNames.values()[taskNo])
      {
      case Start:
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
               .withNodeName(getSubFlow().getIdMap().getCounter().getPrefixId())
               .withTaskName(getSubFlow().getTaskNames()[getSubFlow().getTaskNo()].toString());

         if(previousEntry != null)
         {
            newEntry.withParent(previousEntry);
         }

         boolean lastAction = getSubFlow().getTaskNo() + 1 >= getSubFlow().getTaskNames().length;

         getSubFlow().run();

         if (lastAction)
         {
            switchTo(startPeer);
         }
         break;
      case Collect:
         JsonArray jsonArray = getSubFlow().getIdMap().toJsonArray(this);

         //dump json file
         StoryboardManager.printFile(new File("doc/Logger.json"), jsonArray.toString(3));

         dumpDiagram();
         break;
      default:
         break;
      }
   }

   private void dumpDiagram() 
   {
	   new JsonToGraphViz().dumpSwimlanes(getSubFlow().getClass().getSimpleName(), getEntries());
   }

   private StringBuilder dumpSwimlanes() {
      StringBuilder swimlanesText = new StringBuilder();

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
                  "    }\n"+
                  "    \n"
               );

         StringBuilder innerTasksText = dumpInnerTasks(nodeName);

         CGUtil.replaceAll(laneText, 
            "LaneName", nodeName, 
            "innerTasks", innerTasksText.toString());

         swimlanesText.append(laneText.toString());
      }
      return swimlanesText;
   }

   private StringBuilder dumpInnerTasks(String nodeName) {
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
         innerTasksText.append("        " + nodeName+ "_" + taskName + "[label=\"" + taskName + "\"];\n");
      }

      return innerTasksText;
   }

   private StringBuilder dumpLinks() {
      StringBuilder linksText = new StringBuilder();

      for (LogEntry entry : this.getEntries())
      {
         LogEntry previousEntry = entry.getParent();
         if (previousEntry != null)
         {
            linksText.append("    " +
                  previousEntry.getNodeName() + "_" + previousEntry.getTaskName() +
                  " -> " +
                  entry.getNodeName() + "_" + entry.getTaskName() +
                  "; \n");
         }
      }

      return linksText;
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

      if (PROPERTY_STARTPEER.equalsIgnoreCase(attribute))
      {
         return getStartPeer();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }

      return super.get(attrName);
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
         setIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
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

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((Logger) value);
         return true;
      }

      if (PROPERTY_STARTPEER.equalsIgnoreCase(attrName))
      {
         setStartPeer((PeerProxy) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
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
         return new LogEntrySet();
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

   public static final LoggerSet EMPTY_SET = new LoggerSet();


   //==========================================================================

   public static final String PROPERTY_STARTPEER = "startPeer";

   private PeerProxy startPeer;

   public PeerProxy getStartPeer()
   {
      return this.startPeer;
   }

   public void setStartPeer(PeerProxy value)
   {
      if (this.startPeer != value)
      {
         PeerProxy oldValue = this.startPeer;
         this.startPeer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STARTPEER, oldValue, value);
      }
   }

   public Logger withStartPeer(PeerProxy value)
   {
      setStartPeer(value);
      return this;
   } 
}

