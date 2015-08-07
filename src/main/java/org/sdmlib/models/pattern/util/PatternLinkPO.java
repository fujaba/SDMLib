package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;

public class PatternLinkPO extends PatternObject<PatternLinkPO, PatternLink>
{
   public PatternLinkPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PatternLinkPO(PatternLink... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public PatternLinkPO hasTgtRoleName(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).withTgtRoleName(value);
      }
      return this;
   }
   
   public PatternLinkPO hasHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).withHostGraphSrcObject(value);
      }
      return this;
   }
   
   public PatternLinkPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternLinkPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternObjectPO hasTgt()
   {
      PatternObjectPO result = new PatternObjectPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternLink.PROPERTY_TGT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternLinkPO hasTgt(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternLink.PROPERTY_TGT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withTgt(PatternObjectPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) this.getCurrentMatch()).withTgt((PatternObject) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO hasSrc()
   {
      PatternObjectPO result = new PatternObjectPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternLink.PROPERTY_SRC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternLinkPO hasSrc(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternLink.PROPERTY_SRC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO withSrc(PatternObjectPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) this.getCurrentMatch()).withSrc((PatternObject) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getTgtRoleName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public PatternObject getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) this.getCurrentMatch()).getTgt();
      }
      return null;
   }
   
   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternLink) this.getCurrentMatch()).getSrc();
      }
      return null;
   }
   
   public PatternLinkPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_DOALLMATCHES)
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
         return ((PatternLink) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternLinkPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_PATTERNOBJECTNAME)
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
         return ((PatternLink) getCurrentMatch()).getPatternObjectName();
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

   public PatternLinkPO hasPattern(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }
   
   
   public Pattern getPattern()
   {
      if (super.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }


   public PatternLinkPO hasTgtRoleName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO hasHostGraphSrcObject(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternLinkPO createTgtRoleName(String value)
   {
      this.startCreate().hasTgtRoleName(value).endCreate();
      return this;
   }
   
   public PatternLinkPO createHostGraphSrcObject(Object value)
   {
      this.startCreate().hasHostGraphSrcObject(value).endCreate();
      return this;
   }
   
   public PatternLinkPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public PatternLinkPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public PatternLinkPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public PatternLinkPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public PatternLinkPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

}








