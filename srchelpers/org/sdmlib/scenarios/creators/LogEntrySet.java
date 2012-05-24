package org.sdmlib.scenarios.creators;

import java.util.LinkedHashSet;

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
}

