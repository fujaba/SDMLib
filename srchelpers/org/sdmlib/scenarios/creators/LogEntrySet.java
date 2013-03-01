package org.sdmlib.scenarios.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.scenarios.KanbanEntry;
import org.sdmlib.scenarios.LogEntry;

public class LogEntrySet extends LinkedHashSet<LogEntry>
{
   private static final long serialVersionUID = 1L;

   public KanbanEntrySet getKanbanEntry()
   {
      KanbanEntrySet result = new KanbanEntrySet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getKanbanEntry());
      }
      
      return result;
   }
   public LogEntrySet withKanbanEntry(KanbanEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withKanbanEntry(value);
      }
      
      return this;
   }



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
      return "org.sdmlib.scenarios.LogEntry";
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
}



