package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.LinkConstraint;

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
   
   public TaskFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
   public TaskFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public TaskFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskFlow) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_SUBFLOW)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getSubFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getSubFlow();
      }
      return null;
   }

   public TaskFlowPO hasParent()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO hasParent(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public TaskFlowPO hasTaskNo(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
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
   
   public TaskFlowPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public TaskFlowPO createSubFlow()
   {
      return this.startCreate().hasSubFlow().endCreate();
   }

   public TaskFlowPO createSubFlow(TaskFlowPO tgt)
   {
      return this.startCreate().hasSubFlow(tgt).endCreate();
   }

   public TaskFlowPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public TaskFlowPO createParent(TaskFlowPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

}


