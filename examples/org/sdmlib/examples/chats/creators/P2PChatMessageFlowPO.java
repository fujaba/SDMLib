package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.P2PChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class P2PChatMessageFlowPO extends PatternObject<P2PChatMessageFlowPO, P2PChatMessageFlow>
{
   public P2PChatMessageFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      P2PChatMessageFlowSet matches = new P2PChatMessageFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((P2PChatMessageFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((P2PChatMessageFlow) getCurrentMatch()).run();
      }
   }

   public P2PChatMessageFlowPO hasMsg(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PChatMessageFlow.PROPERTY_MSG)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getMsg()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PChatMessageFlow) getCurrentMatch()).getMsg();
      }
      return null;
   }
   
   public P2PChatMessageFlowPO withMsg(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PChatMessageFlow) getCurrentMatch()).setMsg(value);
      }
      return this;
   }
   
   public P2PChatMessageFlowPO hasPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PChatMessageFlow.PROPERTY_POS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PChatMessageFlow) getCurrentMatch()).getPos();
      }
      return 0;
   }
   
   public P2PChatMessageFlowPO withPos(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PChatMessageFlow) getCurrentMatch()).setPos(value);
      }
      return this;
   }
   
   public P2PChatMessageFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PChatMessageFlow.PROPERTY_TASKNO)
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
         return ((P2PChatMessageFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public P2PChatMessageFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PChatMessageFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
   public P2PChatMessageFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PChatMessageFlow.PROPERTY_IDMAP)
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
         return ((P2PChatMessageFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public P2PChatMessageFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PChatMessageFlow) getCurrentMatch()).setIdMap(value);
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

   public P2PChatMessageFlowPO hasSubFlow(TaskFlowPO tgt)
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

   public P2PChatMessageFlowPO hasParent(TaskFlowPO tgt)
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

}

