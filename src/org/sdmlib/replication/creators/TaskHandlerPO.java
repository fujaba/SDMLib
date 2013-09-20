package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.TaskHandler;
import org.sdmlib.replication.creators.TaskHandlerSet;

public class TaskHandlerPO extends PatternObject<TaskHandlerPO, TaskHandler>
{
   public TaskHandlerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskHandlerSet matches = new TaskHandlerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskHandler) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

