package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.UnifyGraphsOp;

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


   public UnifyGraphsOpPO(){
      newInstance(null);
   }

   public UnifyGraphsOpPO(UnifyGraphsOp... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UnifyGraphsOpPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UnifyGraphsOpPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_MODIFIER)
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
   
   public UnifyGraphsOpPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_HASMATCH)
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
         return ((UnifyGraphsOp) getCurrentMatch()).isHasMatch();
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
   
   public UnifyGraphsOpPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_PATTERNOBJECTNAME)
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
   
   public UnifyGraphsOpPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UnifyGraphsOpPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(UnifyGraphsOp.PROPERTY_DOALLMATCHES)
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
         return ((UnifyGraphsOp) getCurrentMatch()).isDoAllMatches();
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

   public UnifyGraphsOpPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public UnifyGraphsOpPO createPatternLink(PatternPO tgt, String modifier)
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
