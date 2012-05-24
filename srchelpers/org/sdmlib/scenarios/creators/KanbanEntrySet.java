package org.sdmlib.scenarios.creators;

import java.util.LinkedHashSet;

import org.sdmlib.scenarios.KanbanEntry;

public class KanbanEntrySet extends LinkedHashSet<KanbanEntry>
{
   private static final long serialVersionUID = 1L;

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (KanbanEntry obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }
}

