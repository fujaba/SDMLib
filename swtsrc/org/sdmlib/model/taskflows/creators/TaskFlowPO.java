package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
   public TaskFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
}



