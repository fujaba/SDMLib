package org.sdmlib.logger.util;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.models.pattern.PatternObject;

public class TaskFlowPO extends PatternObject<TaskFlowPO, TaskFlow>
{
   public TaskFlowPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskFlowPO(TaskFlow... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }

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

