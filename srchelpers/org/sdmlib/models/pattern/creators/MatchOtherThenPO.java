package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.creators.MatchOtherThenSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.MatchOtherThenPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternObjectPO;

public class MatchOtherThenPO extends PatternObject<MatchOtherThenPO, MatchOtherThen>
{
   public MatchOtherThenSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MatchOtherThenSet matches = new MatchOtherThenSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MatchOtherThen) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public MatchOtherThenPO hasHostGraphSrcObject(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT)
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
         return ((MatchOtherThen) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public MatchOtherThenPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThen) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public MatchOtherThenPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_MODIFIER)
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
         return ((MatchOtherThen) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public MatchOtherThenPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThen) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public MatchOtherThenPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HASMATCH)
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
         return ((MatchOtherThen) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public MatchOtherThenPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThen) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public MatchOtherThenPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_PATTERNOBJECTNAME)
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
         return ((MatchOtherThen) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public MatchOtherThenPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThen) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public MatchOtherThenPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_DOALLMATCHES)
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
         return ((MatchOtherThen) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public MatchOtherThenPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MatchOtherThen) getCurrentMatch()).setDoAllMatches(value);
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

   public MatchOtherThenPO hasPattern(PatternPO tgt)
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
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

   public PatternObjectPO hasSrc()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MatchOtherThen.PROPERTY_SRC, result);
      
      return result;
   }

   public MatchOtherThenPO hasSrc(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MatchOtherThen.PROPERTY_SRC)
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
         return ((MatchOtherThen) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public PatternObjectPO hasForbidden()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MatchOtherThen.PROPERTY_FORBIDDEN, result);
      
      return result;
   }

   public MatchOtherThenPO hasForbidden(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MatchOtherThen.PROPERTY_FORBIDDEN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PatternObject getForbidden()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThen) this.getCurrentMatch()).getForbidden();
      }
      return null;
   }

   public MatchOtherThenPO hasHostGraphSrcObject(Object lower, Object upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThenPO hasModifier(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThenPO hasHasMatch(boolean lower, boolean upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThenPO hasPatternObjectName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThenPO hasDoAllMatches(boolean lower, boolean upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchOtherThenPO createHostGraphSrcObject(Object value)
   {
      this.startCreate().hasHostGraphSrcObject(value).endCreate();
      return this;
   }
   
   public MatchOtherThenPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public MatchOtherThenPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public MatchOtherThenPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public MatchOtherThenPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return this.startCreate().hasPattern().endCreate();
   }

   public MatchOtherThenPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public PatternObjectPO createSrc()
   {
      return this.startCreate().hasSrc().endCreate();
   }

   public MatchOtherThenPO createSrc(PatternObjectPO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public PatternObjectPO createForbidden()
   {
      return this.startCreate().hasForbidden().endCreate();
   }

   public MatchOtherThenPO createForbidden(PatternObjectPO tgt)
   {
      return this.startCreate().hasForbidden(tgt).endCreate();
   }

}



