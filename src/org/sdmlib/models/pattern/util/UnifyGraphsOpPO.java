package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.UnifyGraphsOp;

public class UnifyGraphsOpPO extends PatternObject<UnifyGraphsOpPO, UnifyGraphsOp>
{
   public UnifyGraphsOpPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public UnifyGraphsOpPO(UnifyGraphsOp... hostGraphObject) {
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public UnifyGraphsOpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UnifyGraphsOpSet matches = new UnifyGraphsOpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UnifyGraphsOp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public UnifyGraphsOpPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   @Override
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   @Override
   public UnifyGraphsOpPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UnifyGraphsOp) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public UnifyGraphsOpPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   @Override
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   @Override
   public UnifyGraphsOpPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UnifyGraphsOp) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public UnifyGraphsOpPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   @Override
   public String getPatternObjectName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   @Override
   public UnifyGraphsOpPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UnifyGraphsOp) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public UnifyGraphsOpPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   @Override
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   @Override
   public UnifyGraphsOpPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UnifyGraphsOp) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }
   
   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public UnifyGraphsOpPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   @Override
   public Pattern getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

   public UnifyGraphsOpPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UnifyGraphsOpPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UnifyGraphsOpPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UnifyGraphsOpPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UnifyGraphsOpPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public UnifyGraphsOpPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public UnifyGraphsOpPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public UnifyGraphsOpPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   @Override
   public PatternPO createPattern()
   {
      return this.startCreate().hasPattern().endCreate();
   }

   public UnifyGraphsOpPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

}
