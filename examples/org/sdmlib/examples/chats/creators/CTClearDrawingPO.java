package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.examples.chats.CTClearDrawing;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CTClearDrawingPO extends PatternObject<CTClearDrawingPO, CTClearDrawing>
{
   public CTClearDrawingSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CTClearDrawingSet matches = new CTClearDrawingSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CTClearDrawing) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public CTClearDrawingPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTClearDrawing.PROPERTY_TASKNO)
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
         return ((CTClearDrawing) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public CTClearDrawingPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTClearDrawing.PROPERTY_IDMAP)
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
         return ((CTClearDrawing) getCurrentMatch()).getIdMap();
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

   public CTClearDrawingPO hasSubFlow(TaskFlowPO tgt)
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

   public CTClearDrawingPO hasParent(TaskFlowPO tgt)
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

   public CTClearDrawingPO hasParent(CSVisitAllClientsFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(CSClientTask.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

}



