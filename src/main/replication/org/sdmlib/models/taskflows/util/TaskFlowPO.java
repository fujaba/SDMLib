package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.SDMLibJsonIdMap;

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
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
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
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TaskFlowPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
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
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlow()
   {
      return this.startCreate().hasSubFlow().endCreate();
   }

   public TaskFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }

   public TaskFlowPO createSubFlow(TaskFlowPO tgt)
   {
      return this.startCreate().hasSubFlow(tgt).endCreate();
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
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public TaskFlowPO hasParent(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public TaskFlowPO createParent(TaskFlowPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public TaskFlowPO filterTaskNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO filterTaskNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO filterIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO filterParent()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO filterParent(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public TaskFlowPO filterSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO filterSubFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }


   public TaskFlowPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TaskFlowPO createIdMapCondition(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createIdMapAssignment(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTaskNoCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTaskNoCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTaskNoAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createParentPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParentPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParentLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public TaskFlowPO createParentLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT, modifier);
   }

   public TaskFlowPO createSubFlowPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlowPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlowLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }

   public TaskFlowPO createSubFlowLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW, modifier);
   }

}
