package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.OptionalSubPatternSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternElementPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.OptionalSubPatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.creators.PatternElementSet;
import org.sdmlib.models.pattern.creators.PatternPO;

public class OptionalSubPatternPO extends PatternObject<OptionalSubPatternPO, OptionalSubPattern>
{
   public OptionalSubPatternSet allMatches()
   {
      this.setDoAllMatches(true);
      
      OptionalSubPatternSet matches = new OptionalSubPatternSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((OptionalSubPattern) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public OptionalSubPatternPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MODIFIER)
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
         return ((OptionalSubPattern) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public OptionalSubPatternPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_HASMATCH)
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
         return ((OptionalSubPattern) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public OptionalSubPatternPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME)
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
         return ((OptionalSubPattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public OptionalSubPatternPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DOALLMATCHES)
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
         return ((OptionalSubPattern) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public OptionalSubPatternPO hasMatchForward(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MATCHFORWARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getMatchForward()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).getMatchForward();
      }
      return false;
   }
   
   public OptionalSubPatternPO hasCurrentSubPattern(Pattern value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_CURRENTSUBPATTERN)
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
         return ((OptionalSubPattern) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
   public OptionalSubPatternPO hasDebugMode(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DEBUGMODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getDebugMode()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).getDebugMode();
      }
      return 0;
   }
   
   public OptionalSubPatternPO withDebugMode(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setDebugMode(value);
      }
      return this;
   }
   
   public PatternElementPO hasElements()
   {
      PatternElementPO result = new PatternElementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);
      
      return result;
   }

   public OptionalSubPatternPO hasElements(PatternElementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pattern.PROPERTY_ELEMENTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PatternElementSet getElements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }

   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public OptionalSubPatternPO hasPattern(PatternPO tgt)
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

   public OptionalSubPatternPO hasTrace(StringBuilder value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_TRACE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StringBuilder getTrace()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).getTrace();
      }
      return null;
   }
   
   public OptionalSubPatternPO withTrace(StringBuilder value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setTrace(value);
      }
      return this;
   }
   
}




