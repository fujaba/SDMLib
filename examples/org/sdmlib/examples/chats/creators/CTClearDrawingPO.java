package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.CTClearDrawing;
import org.sdmlib.examples.chats.creators.CTClearDrawingSet;
import org.sdmlib.models.pattern.AttributeConstraint;
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
   
}


