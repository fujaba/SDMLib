package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

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
   
}


