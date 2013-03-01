package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

public class NegativeApplicationConditionPO extends PatternObject
{
   public NegativeApplicationConditionPO withCurrentNAC(NegativeApplicationCondition value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withCurrentSubPattern(value);
      }
      return this;
   }
   
   public NegativeApplicationConditionPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public NegativeApplicationConditionPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public NegativeApplicationConditionPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_DOALLMATCHES)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public NegativeApplicationConditionPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public NegativeApplicationConditionPO hasCurrentSubPattern(Pattern value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_CURRENTSUBPATTERN)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
}






