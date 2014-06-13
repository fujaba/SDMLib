package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.logger.TaskFlow;
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


   public TaskFlowPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskFlowPO(TaskFlow... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TaskFlowPO hasTaskNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlowCreator.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TaskFlowPO hasTaskNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlowCreator.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TaskFlowPO createTaskNo(int value)
   {
      this.startCreate().hasTaskNo(value).endCreate();
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
   
   public TaskFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
}
