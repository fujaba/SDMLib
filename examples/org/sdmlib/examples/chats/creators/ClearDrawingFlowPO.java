package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.ClearDrawingFlow;
import org.sdmlib.examples.chats.creators.ClearDrawingFlowSet;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ClearDrawingFlowPO extends PatternObject<ClearDrawingFlowPO, ClearDrawingFlow>
{
   public ClearDrawingFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ClearDrawingFlowSet matches = new ClearDrawingFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ClearDrawingFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((ClearDrawingFlow) getCurrentMatch()).run();
      }
   }

   public ClearDrawingFlowPO hasGui(PeerToPeerChat value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClearDrawingFlow.PROPERTY_GUI)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerToPeerChat getGui()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClearDrawingFlow) getCurrentMatch()).getGui();
      }
      return null;
   }
   
   public ClearDrawingFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClearDrawingFlow.PROPERTY_TASKNO)
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
         return ((ClearDrawingFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
}

