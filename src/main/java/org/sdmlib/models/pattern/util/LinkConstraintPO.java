package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;

public class LinkConstraintPO extends PatternObject<LinkConstraintPO, LinkConstraint>
{
   public LinkConstraintPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LinkConstraintPO(LinkConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public LinkConstraintPO hasTgtRoleName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setTgtRoleName(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public String getTgtRoleName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public LinkConstraintPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_DOALLMATCHES)
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
         return ((LinkConstraint) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public LinkConstraintPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((LinkConstraint) getCurrentMatch()).getPatternObjectName();
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

   public LinkConstraintPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Pattern getPattern()
   {
      if (super.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }


   public PatternObjectPO hasTgt()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternLink.PROPERTY_TGT, result);
      
      return result;
   }

   public LinkConstraintPO hasTgt(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternLink.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PatternObject getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

   public PatternObjectPO hasSrc()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternLink.PROPERTY_SRC, result);
      
      return result;
   }

   public LinkConstraintPO hasSrc(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternLink.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public LinkConstraintPO hasTgtRoleName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO hasHostGraphSrcObject(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO createTgtRoleName(String value)
   {
      this.startCreate().hasTgtRoleName(value).endCreate();
      return this;
   }
   
   public LinkConstraintPO createHostGraphSrcObject(Object value)
   {
      this.startCreate().hasHostGraphSrcObject(value).endCreate();
      return this;
   }
   
   public LinkConstraintPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public LinkConstraintPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public LinkConstraintPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public LinkConstraintPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public LinkConstraintPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public LinkConstraintPO filterTgtRoleName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterTgtRoleName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO filterDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}








