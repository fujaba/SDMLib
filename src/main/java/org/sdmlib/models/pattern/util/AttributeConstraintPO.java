package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.AttributeConstraintPO;
import org.sdmlib.models.pattern.util.PatternObjectPO;

public class AttributeConstraintPO extends PatternObject<AttributeConstraintPO, AttributeConstraint>
{

    public AttributeConstraintSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AttributeConstraintSet matches = new AttributeConstraintSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((AttributeConstraint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AttributeConstraintPO(){
      newInstance(null);
   }

   public AttributeConstraintPO(AttributeConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public AttributeConstraintPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public AttributeConstraintPO createAttrNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createAttrNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_ATTRNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createAttrNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getAttrName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) getCurrentMatch()).getAttrName();
      }
      return null;
   }
   
   public AttributeConstraintPO withAttrName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setAttrName(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createTgtValueCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_TGTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createTgtValueAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_TGTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getTgtValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) getCurrentMatch()).getTgtValue();
      }
      return null;
   }
   
   public AttributeConstraintPO withTgtValue(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setTgtValue(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createUpperTgtValueCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_UPPERTGTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createUpperTgtValueAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_UPPERTGTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getUpperTgtValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) getCurrentMatch()).getUpperTgtValue();
      }
      return null;
   }
   
   public AttributeConstraintPO withUpperTgtValue(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setUpperTgtValue(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createCmpOpCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_CMPOP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createCmpOpCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_CMPOP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createCmpOpAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_CMPOP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getCmpOp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) getCurrentMatch()).getCmpOp();
      }
      return null;
   }
   
   public AttributeConstraintPO withCmpOp(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setCmpOp(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createHostGraphSrcObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createHostGraphSrcObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public AttributeConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_MODIFIER)
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
         return ((AttributeConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public AttributeConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HASMATCH)
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
         return ((AttributeConstraint) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public AttributeConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((AttributeConstraint) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public AttributeConstraintPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public AttributeConstraintPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AttributeConstraintPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_DOALLMATCHES)
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
         return ((AttributeConstraint) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public AttributeConstraintPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).setDoAllMatches(value);
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

   public AttributeConstraintPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public AttributeConstraintPO createPatternLink(PatternPO tgt, String modifier)
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

   public PatternObjectPO createSrcPO()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(AttributeConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public PatternObjectPO createSrcPO(String modifier)
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(AttributeConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public AttributeConstraintPO createSrcLink(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, AttributeConstraint.PROPERTY_SRC);
   }

   public AttributeConstraintPO createSrcLink(PatternObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, AttributeConstraint.PROPERTY_SRC, modifier);
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeConstraint) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

}
