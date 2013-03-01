package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.examples.chats.CTDrawPoint;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CTDrawPointPO extends PatternObject<CTDrawPointPO, CTDrawPoint>
{
   public CTDrawPointSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CTDrawPointSet matches = new CTDrawPointSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CTDrawPoint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public CTDrawPointPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_TASKNO)
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
         return ((CTDrawPoint) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public CTDrawPointPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CTDrawPoint) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public CTDrawPointPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CTDrawPoint) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public CTDrawPointPO hasR(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_R)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getR()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CTDrawPoint) getCurrentMatch()).getR();
      }
      return 0;
   }
   
   public CTDrawPointPO hasG(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_G)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getG()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CTDrawPoint) getCurrentMatch()).getG();
      }
      return 0;
   }
   
   public CTDrawPointPO hasB(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_B)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getB()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CTDrawPoint) getCurrentMatch()).getB();
      }
      return 0;
   }
   
   public CTDrawPointPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CTDrawPoint.PROPERTY_IDMAP)
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
         return ((CTDrawPoint) getCurrentMatch()).getIdMap();
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

   public CTDrawPointPO hasSubFlow(TaskFlowPO tgt)
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

   public CTDrawPointPO hasParent(TaskFlowPO tgt)
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

   public CTDrawPointPO hasParent(CSVisitAllClientsFlowPO tgt)
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



