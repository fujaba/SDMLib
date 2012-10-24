package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.TaskFlow;

public class LogEntrySet extends LinkedHashSet<LogEntry> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LogEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.LogEntry";
   }


   public LogEntrySet with(LogEntry value)
   {
      this.add(value);
      return this;
   }
   
   public LogEntrySet without(LogEntry value)
   {
      this.remove(value);
      return this;
   }
   public StringList getNodeName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getNodeName());
      }
      
      return result;
   }

   public LogEntrySet withNodeName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.withNodeName(value);
      }
      
      return this;
   }

   public StringList getTaskName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTaskName());
      }
      
      return result;
   }

   public LogEntrySet withTaskName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.withTaskName(value);
      }
      
      return this;
   }

   public LoggerSet getLogger()
   {
      LoggerSet result = new LoggerSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getLogger());
      }
      
      return result;
   }
   public LogEntrySet withLogger(Logger value)
   {
      for (LogEntry obj : this)
      {
         obj.withLogger(value);
      }
      
      return this;
   }

}


