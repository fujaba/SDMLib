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
   
package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.model.taskflows.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import org.sdmlib.model.taskflows.LogEntry;

public class LoggerSet extends LinkedHashSet<Logger> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Logger elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.Logger";
   }


   public LoggerSet with(Logger value)
   {
      this.add(value);
      return this;
   }
   
   public LoggerSet without(Logger value)
   {
      this.remove(value);
      return this;
   }
   public PeerProxySet getStartPeer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (Logger obj : this)
      {
         result.add(obj.getStartPeer());
      }
      
      return result;
   }

   public LoggerSet withStartPeer(PeerProxy value)
   {
      for (Logger obj : this)
      {
         obj.setStartPeer(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (Logger obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public LoggerSet withTaskNo(int value)
   {
      for (Logger obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public LoggerSet withIdMap(SDMLibJsonIdMap value)
   {
      for (Logger obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public LoggerSet withSubFlow(TaskFlow value)
   {
      for (Logger obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public LoggerSet withParent(TaskFlow value)
   {
      for (Logger obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public LogEntrySet getEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (Logger obj : this)
      {
         result.addAll(obj.getEntries());
      }
      
      return result;
   }

   public LoggerSet withEntries(LogEntry value)
   {
      for (Logger obj : this)
      {
         obj.withEntries(value);
      }
      
      return this;
   }

   public LoggerSet withoutEntries(LogEntry value)
   {
      for (Logger obj : this)
      {
         obj.withoutEntries(value);
      }
      
      return this;
   }

}

