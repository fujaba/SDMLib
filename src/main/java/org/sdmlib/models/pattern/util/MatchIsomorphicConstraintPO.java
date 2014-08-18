package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

public class MatchIsomorphicConstraintPO extends PatternObject<MatchIsomorphicConstraintPO, MatchIsomorphicConstraint>
{
   public MatchIsomorphicConstraintPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MatchIsomorphicConstraintPO(MatchIsomorphicConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public MatchIsomorphicConstraintPO startNAC()
   {
      super.startNAC();
      return this;
   }
   
   @Override
   public MatchIsomorphicConstraintPO endNAC()
   {
      return (MatchIsomorphicConstraintPO) super.endNAC();
   }
   
   public MatchIsomorphicConstraintSet allMatches()
   {
      this.setDoAllMatches(true);
      
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
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public MatchIsomorphicConstraintPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_HASMATCH)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_DOALLMATCHES)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((MatchIsomorphicConstraint) getCurrentMatch()).getPatternObjectName();
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

   public MatchIsomorphicConstraintPO hasPattern(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   @Override
   public Pattern getPattern()
   {
      if (super.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }


   public MatchIsomorphicConstraintPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public MatchIsomorphicConstraintPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public MatchIsomorphicConstraintPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public MatchIsomorphicConstraintPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   @Override
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public MatchIsomorphicConstraintPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

}






