package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

public class MatchIsomorphicConstraintPO extends PatternObject<MatchIsomorphicConstraintPO, MatchIsomorphicConstraint>
{

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


   public MatchIsomorphicConstraintPO(){
      newInstance(null);
   }

   public MatchIsomorphicConstraintPO(MatchIsomorphicConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MatchIsomorphicConstraintPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MatchIsomorphicConstraintPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public MatchIsomorphicConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchIsomorphicConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public MatchIsomorphicConstraintPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchIsomorphicConstraint) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchIsomorphicConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public MatchIsomorphicConstraintPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public MatchIsomorphicConstraintPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchIsomorphicConstraint) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public MatchIsomorphicConstraintPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchIsomorphicConstraintPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchIsomorphicConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchIsomorphicConstraint) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public MatchIsomorphicConstraintPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchIsomorphicConstraint) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }
   
   public PatternPO createPatternPO()
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public PatternPO createPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(modifier);
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public MatchIsomorphicConstraintPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public MatchIsomorphicConstraintPO createPatternLink(PatternPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN, modifier);
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
