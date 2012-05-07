package org.sdmlib.scenarios.creators;

import java.util.LinkedHashSet;
import org.sdmlib.scenarios.KanbanEntry;
import org.sdmlib.scenarios.LogEntry;

public class KanbanEntrySet extends LinkedHashSet<KanbanEntry>
{
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

