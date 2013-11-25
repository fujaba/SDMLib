package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.UnifyGraphsOp;
import org.sdmlib.models.pattern.creators.UnifyGraphsOpSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.UnifyGraphsOpPO;
import org.sdmlib.models.pattern.Pattern;

public class UnifyGraphsOpPO extends PatternObject<UnifyGraphsOpPO, UnifyGraphsOp>
{
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getPatternObjectName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UnifyGraphsOp) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
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

   public Pattern getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

}

