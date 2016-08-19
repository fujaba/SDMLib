package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.GenericConstraint;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.GenericConstraintPO;

public class GenericConstraintPO extends PatternObject<GenericConstraintPO, GenericConstraint>
{

    public GenericConstraintSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GenericConstraintSet matches = new GenericConstraintSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((GenericConstraint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GenericConstraintPO(){
      newInstance(null);
   }

   public GenericConstraintPO(GenericConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GenericConstraintPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GenericConstraintPO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericConstraint) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public GenericConstraintPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GenericConstraint) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public GenericConstraintPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_MODIFIER)
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
         return ((GenericConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public GenericConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GenericConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public GenericConstraintPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_HASMATCH)
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
         return ((GenericConstraint) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public GenericConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GenericConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public GenericConstraintPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((GenericConstraint) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public GenericConstraintPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GenericConstraint) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public GenericConstraintPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericConstraintPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(GenericConstraint.PROPERTY_DOALLMATCHES)
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
         return ((GenericConstraint) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public GenericConstraintPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GenericConstraint) getCurrentMatch()).setDoAllMatches(value);
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

   public GenericConstraintPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public GenericConstraintPO createPatternLink(PatternPO tgt, String modifier)
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
