package org.sdmlib.logger.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.logger.TaskFlow;
import org.sdmlib.examples.adamandeve.model.creators.TaskFlowSet;

public class TaskFlowPO extends PatternObject<TaskFlowPO, TaskFlow>
{

    public TaskFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskFlowSet matches = new TaskFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

