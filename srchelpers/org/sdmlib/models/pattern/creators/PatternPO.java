package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternElementPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.creators.PatternSet;
import org.sdmlib.models.pattern.creators.PatternElementSet;

public class PatternPO extends PatternObject
{
   public PatternPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternElementPO hasElements()
   {
      PatternElementPO result = new PatternElementPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pattern.PROPERTY_ELEMENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternPO hasElements(PatternElementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pattern.PROPERTY_ELEMENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withElements(PatternElementPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) this.getCurrentMatch()).withElements((PatternElement) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternPO withoutElements(PatternElementPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) this.getCurrentMatch()).withoutElements((PatternElement) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public PatternElementSet getElements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }
   
   public PatternPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DOALLMATCHES)
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
         return ((Pattern) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
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
         return ((Pattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO hasCurrentSubPattern(Pattern value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_CURRENTSUBPATTERN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Pattern getCurrentSubPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
}






