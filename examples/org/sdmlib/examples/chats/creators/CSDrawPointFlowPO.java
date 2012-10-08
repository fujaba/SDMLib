package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.CSDrawPointFlow;
import org.sdmlib.examples.chats.creators.CSDrawPointFlowSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CSDrawPointFlowPO extends PatternObject<CSDrawPointFlowPO, CSDrawPointFlow>
{
   public CSDrawPointFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSDrawPointFlowSet matches = new CSDrawPointFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CSDrawPointFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((CSDrawPointFlow) getCurrentMatch()).run();
      }
   }

   public CSDrawPointFlowPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_X)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public CSDrawPointFlowPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_Y)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public CSDrawPointFlowPO hasR(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_R)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getR();
      }
      return 0;
   }
   
   public CSDrawPointFlowPO hasG(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_G)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getG();
      }
      return 0;
   }
   
   public CSDrawPointFlowPO hasB(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_B)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getB();
      }
      return 0;
   }
   
   public CSDrawPointFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_IDMAP)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public CSDrawPointFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSDrawPointFlow.PROPERTY_TASKNO)
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
         return ((CSDrawPointFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
}

