package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.PeerProxy;

public class LoggerSet extends LinkedHashSet<Logger> implements ModelSet
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
         obj.withTaskNo(value);
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
         obj.withIdMap(value);
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

   public TaskFlowSet getTargetTaskFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getTargetTaskFlow());
      }
      
      return result;
   }
   public LoggerSet withTargetTaskFlow(TaskFlow value)
   {
      for (Logger obj : this)
      {
         obj.withTargetTaskFlow(value);
      }
      
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
         obj.withStartPeer(value);
      }
      
      return this;
   }

}



