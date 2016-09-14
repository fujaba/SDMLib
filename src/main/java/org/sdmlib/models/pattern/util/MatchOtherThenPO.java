package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.MatchOtherThenPO;
import org.sdmlib.models.pattern.util.PatternObjectPO;

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


   public MatchOtherThenPO(){
      newInstance(null);
   }

   public MatchOtherThenPO(MatchOtherThen... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MatchOtherThenPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MatchOtherThenPO createHostGraphSrcObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createHostGraphSrcObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT)
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
   
   public MatchOtherThenPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_MODIFIER)
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
   
   public MatchOtherThenPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_HASMATCH)
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
         return ((MatchOtherThen) getCurrentMatch()).isHasMatch();
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
   
   public MatchOtherThenPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_PATTERNOBJECTNAME)
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
   
   public MatchOtherThenPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchOtherThenPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(MatchOtherThen.PROPERTY_DOALLMATCHES)
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
         return ((MatchOtherThen) getCurrentMatch()).isDoAllMatches();
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

   public MatchOtherThenPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public MatchOtherThenPO createPatternLink(PatternPO tgt, String modifier)
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
      super.hasLink(MatchOtherThen.PROPERTY_SRC, result);
      
      return result;
   }

   public PatternObjectPO createSrcPO(String modifier)
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(MatchOtherThen.PROPERTY_SRC, result);
      
      return result;
   }

   public MatchOtherThenPO createSrcLink(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThen.PROPERTY_SRC);
   }

   public MatchOtherThenPO createSrcLink(PatternObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MatchOtherThen.PROPERTY_SRC, modifier);
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThen) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public PatternObjectPO createForbiddenPO()
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MatchOtherThen.PROPERTY_FORBIDDEN, result);
      
      return result;
   }

   public PatternObjectPO createForbiddenPO(String modifier)
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(MatchOtherThen.PROPERTY_FORBIDDEN, result);
      
      return result;
   }

   public MatchOtherThenPO createForbiddenLink(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, MatchOtherThen.PROPERTY_FORBIDDEN);
   }

   public MatchOtherThenPO createForbiddenLink(PatternObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MatchOtherThen.PROPERTY_FORBIDDEN, modifier);
   }

   public PatternObject getForbidden()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MatchOtherThen) this.getCurrentMatch()).getForbidden();
      }
      return null;
   }

}
