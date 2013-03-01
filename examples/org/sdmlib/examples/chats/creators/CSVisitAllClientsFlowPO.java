package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CSVisitAllClientsFlowPO extends PatternObject<CSVisitAllClientsFlowPO, CSVisitAllClientsFlow>
{
   public CSVisitAllClientsFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSVisitAllClientsFlowSet matches = new CSVisitAllClientsFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CSVisitAllClientsFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((CSVisitAllClientsFlow) getCurrentMatch()).run();
      }
   }

   public CSVisitAllClientsFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSVisitAllClientsFlow.PROPERTY_IDMAP)
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
         return ((CSVisitAllClientsFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public CSVisitAllClientsFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSVisitAllClientsFlow.PROPERTY_TASKNO)
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
         return ((CSVisitAllClientsFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public CSClientTaskPO hasTgtTask()
   {
      CSClientTaskPO result = new CSClientTaskPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(CSVisitAllClientsFlow.PROPERTY_TGTTASK, result);
      
      return result;
   }
   
   public CSVisitAllClientsFlowPO hasTgtTask(CSClientTaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(CSVisitAllClientsFlow.PROPERTY_TGTTASK)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CSClientTask getTgtTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CSVisitAllClientsFlow) this.getCurrentMatch()).getTgtTask();
      }
      return null;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public CSVisitAllClientsFlowPO hasSubFlow(TaskFlowPO tgt)
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

   public CSVisitAllClientsFlowPO hasParent(TaskFlowPO tgt)
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


