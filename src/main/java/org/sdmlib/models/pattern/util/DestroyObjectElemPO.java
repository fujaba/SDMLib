package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.DestroyObjectElemPO;
import org.sdmlib.models.pattern.util.PatternObjectPO;

public class DestroyObjectElemPO extends PatternObject<DestroyObjectElemPO, DestroyObjectElem>
{

    public DestroyObjectElemSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DestroyObjectElemSet matches = new DestroyObjectElemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DestroyObjectElem) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DestroyObjectElemPO(){
      newInstance(null);
   }

   public DestroyObjectElemPO(DestroyObjectElem... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public DestroyObjectElemPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public DestroyObjectElemPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
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
         return ((DestroyObjectElem) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public DestroyObjectElemPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((DestroyObjectElem) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public DestroyObjectElemPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_HASMATCH)
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
         return ((DestroyObjectElem) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public DestroyObjectElemPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((DestroyObjectElem) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public DestroyObjectElemPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME)
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
         return ((DestroyObjectElem) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public DestroyObjectElemPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((DestroyObjectElem) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public DestroyObjectElemPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DestroyObjectElemPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_DOALLMATCHES)
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
         return ((DestroyObjectElem) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public DestroyObjectElemPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((DestroyObjectElem) getCurrentMatch()).setDoAllMatches(value);
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

   public DestroyObjectElemPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public DestroyObjectElemPO createPatternLink(PatternPO tgt, String modifier)
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

   public PatternObjectPO createPatternObjectPO()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(DestroyObjectElem.PROPERTY_PATTERNOBJECT, result);
      
      return result;
   }

   public PatternObjectPO createPatternObjectPO(String modifier)
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(DestroyObjectElem.PROPERTY_PATTERNOBJECT, result);
      
      return result;
   }

   public DestroyObjectElemPO createPatternObjectLink(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, DestroyObjectElem.PROPERTY_PATTERNOBJECT);
   }

   public DestroyObjectElemPO createPatternObjectLink(PatternObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, DestroyObjectElem.PROPERTY_PATTERNOBJECT, modifier);
   }

   public PatternObject getPatternObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) this.getCurrentMatch()).getPatternObject();
      }
      return null;
   }

}
