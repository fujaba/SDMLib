package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.MatchOtherThan;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;
import org.sdmlib.models.pattern.util.PatternObjectPO;
import org.sdmlib.models.pattern.util.MatchOtherThanPO;

public class MatchOtherThanPO extends PatternObject<MatchOtherThanPO, MatchOtherThan>
{
   public MatchOtherThanPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MatchOtherThanPO(MatchOtherThan... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public MatchOtherThanSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MatchOtherThanSet matches = new MatchOtherThanSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MatchOtherThan) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public MatchOtherThanPO hasHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThan) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public MatchOtherThanPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThan) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public MatchOtherThanPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThan) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public MatchOtherThanPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThan) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public MatchOtherThanPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThan) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public MatchOtherThanPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThan) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public MatchOtherThanPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_PATTERNOBJECTNAME)
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
         return ((MatchOtherThan) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public MatchOtherThanPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThan) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public MatchOtherThanPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_DOALLMATCHES)
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
         return ((MatchOtherThan) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public MatchOtherThanPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThan) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }
   
   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public MatchOtherThanPO hasPattern(PatternPO tgt)
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


   public PatternObjectPO hasSrc()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MatchOtherThan.PROPERTY_SRC, result);
      
      return result;
   }

   public MatchOtherThanPO hasSrc(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThan.PROPERTY_SRC);
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThan) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public PatternObjectPO hasForbidden()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MatchOtherThan.PROPERTY_FORBIDDEN, result);
      
      return result;
   }

   public MatchOtherThanPO hasForbidden(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThan.PROPERTY_FORBIDDEN);
   }

   public PatternObject getForbidden()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThan) this.getCurrentMatch()).getForbidden();
      }
      return null;
   }

   public MatchOtherThanPO hasHostGraphSrcObject(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThanPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThanPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThanPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThanPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThanPO createHostGraphSrcObject(Object value)
   {
      this.startCreate().hasHostGraphSrcObject(value).endCreate();
      return this;
   }
   
   public MatchOtherThanPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public MatchOtherThanPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public MatchOtherThanPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public MatchOtherThanPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public MatchOtherThanPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public PatternObjectPO createSrc()
   {
      return (PatternObjectPO) this.startCreate().hasSrc().endCreate();
   }

   public MatchOtherThanPO createSrc(PatternObjectPO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public PatternObjectPO createForbidden()
   {
      return this.startCreate().hasForbidden().endCreate();
   }

   public MatchOtherThanPO createForbidden(PatternObjectPO tgt)
   {
      return this.startCreate().hasForbidden(tgt).endCreate();
   }

   public MatchOtherThanPO filterHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThanPO filterDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThan.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternObjectPO filterSrc()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MatchOtherThan.PROPERTY_SRC, result);
      
      return result;
   }

   public MatchOtherThanPO filterSrc(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThan.PROPERTY_SRC);
   }

   public PatternObjectPO filterForbidden()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MatchOtherThan.PROPERTY_FORBIDDEN, result);
      
      return result;
   }

   public MatchOtherThanPO filterForbidden(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThan.PROPERTY_FORBIDDEN);
   }

}



