package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.CSClearDrawingFlow;
import org.sdmlib.examples.chats.creators.CSClearDrawingFlowSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.AttributeConstraint;

public class CSClearDrawingFlowPO extends PatternObject<CSClearDrawingFlowPO, CSClearDrawingFlow>
{
   public CSClearDrawingFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSClearDrawingFlowSet matches = new CSClearDrawingFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CSClearDrawingFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((CSClearDrawingFlow) getCurrentMatch()).run();
      }
   }

   public CSClearDrawingFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSClearDrawingFlow.PROPERTY_IDMAP)
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
         return ((CSClearDrawingFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public CSClearDrawingFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSClearDrawingFlow.PROPERTY_TASKNO)
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
         return ((CSClearDrawingFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
}

