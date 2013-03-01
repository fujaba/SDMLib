package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class MatchIsomorphicConstraintPO extends PatternObject
{
   public MatchIsomorphicConstraintPO startNAC()
   {
      return (MatchIsomorphicConstraintPO) super.startNAC();
   }
   
   public MatchIsomorphicConstraintPO endNAC()
   {
      return (MatchIsomorphicConstraintPO) super.endNAC();
   }
   
   public MatchIsomorphicConstraintSet allMatches()
   {
      MatchIsomorphicConstraintSet matches = new MatchIsomorphicConstraintSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MatchIsomorphicConstraint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public MatchIsomorphicConstraintPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public MatchIsomorphicConstraintPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_HASMATCH)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_DOALLMATCHES)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
}



