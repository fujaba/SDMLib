package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.util.PatternElementPO;

public class PatternElementPO extends PatternObject<PatternElementPO, PatternElement>
{

    public PatternElementSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PatternElementSet matches = new PatternElementSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PatternElement) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PatternElementPO(){
      newInstance(null);
   }

   public PatternElementPO(PatternElement... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PatternElementPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PatternElementPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_MODIFIER)
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
         return ((PatternElement) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public PatternElementPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternElement) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public PatternElementPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_HASMATCH)
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
         return ((PatternElement) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public PatternElementPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternElement) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public PatternElementPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_PATTERNOBJECTNAME)
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
         return ((PatternElement) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternElementPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternElement) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public PatternElementPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternElementPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternElement.PROPERTY_DOALLMATCHES)
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
         return ((PatternElement) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public PatternElementPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternElement) getCurrentMatch()).setDoAllMatches(value);
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

   public PatternElementPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public PatternElementPO createPatternLink(PatternPO tgt, String modifier)
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
