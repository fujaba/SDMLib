package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.CloneOpPO;

public class CloneOpPO extends PatternObject<CloneOpPO, CloneOp>
{

    public CloneOpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CloneOpSet matches = new CloneOpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CloneOp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CloneOpPO(){
      newInstance(null);
   }

   public CloneOpPO(CloneOp... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CloneOpPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CloneOpPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_MODIFIER)
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
         return ((CloneOp) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public CloneOpPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloneOp) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public CloneOpPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_HASMATCH)
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
         return ((CloneOp) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public CloneOpPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloneOp) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public CloneOpPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_PATTERNOBJECTNAME)
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
         return ((CloneOp) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public CloneOpPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloneOp) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public CloneOpPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CloneOpPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CloneOp.PROPERTY_DOALLMATCHES)
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
         return ((CloneOp) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public CloneOpPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloneOp) getCurrentMatch()).setDoAllMatches(value);
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

   public CloneOpPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public CloneOpPO createPatternLink(PatternPO tgt, String modifier)
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
