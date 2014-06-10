package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

public class DestroyObjectElemPO extends PatternObject<DestroyObjectElemPO, DestroyObjectElem>
{
   public DestroyObjectElemPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DestroyObjectElemPO(DestroyObjectElem... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public DestroyObjectElemPO startNAC()
   {
      return (DestroyObjectElemPO) super.startNAC();
   }
   
   @Override
   public DestroyObjectElemPO endNAC()
   {
      return (DestroyObjectElemPO) super.endNAC();
   }
   
   public DestroyObjectElemSet allMatches()
   {
      DestroyObjectElemSet matches = new DestroyObjectElemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DestroyObjectElem) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public DestroyObjectElemPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
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
         return ((DestroyObjectElem) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public DestroyObjectElemPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_HASMATCH)
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
         return ((DestroyObjectElem) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public PatternObjectPO hasPatternObject()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(DestroyObjectElem.PROPERTY_PATTERNOBJECT, result);
      
      return result;
   }
   
   public DestroyObjectElemPO hasPatternObject(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(DestroyObjectElem.PROPERTY_PATTERNOBJECT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObject getPatternObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) this.getCurrentMatch()).getPatternObject();
      }
      return null;
   }
   
   public DestroyObjectElemPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_DOALLMATCHES)
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
         return ((DestroyObjectElem) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public DestroyObjectElemPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME)
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
         return ((DestroyObjectElem) getCurrentMatch()).getPatternObjectName();
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

   public DestroyObjectElemPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   @Override
   public Pattern getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

   public DestroyObjectElemPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DestroyObjectElemPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DestroyObjectElemPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DestroyObjectElemPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DestroyObjectElemPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public DestroyObjectElemPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public DestroyObjectElemPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public DestroyObjectElemPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   @Override
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public DestroyObjectElemPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public PatternObjectPO createPatternObject()
   {
      return (PatternObjectPO) this.startCreate().hasPatternObject().endCreate();
   }

   public DestroyObjectElemPO createPatternObject(PatternObjectPO tgt)
   {
      return this.startCreate().hasPatternObject(tgt).endCreate();
   }

}






