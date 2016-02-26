package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;
import org.sdmlib.models.pattern.util.PatternObjectPO;
import org.sdmlib.models.pattern.util.CardinalityConstraintPO;

public class CardinalityConstraintPO extends PatternObject<CardinalityConstraintPO, CardinalityConstraint>
{
   public CardinalityConstraintPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CardinalityConstraintPO(CardinalityConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public CardinalityConstraintSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CardinalityConstraintSet matches = new CardinalityConstraintSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CardinalityConstraint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public CardinalityConstraintPO hasTgtRoleName(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTgtRoleName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CardinalityConstraint) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public CardinalityConstraintPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setTgtRoleName(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
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
         return ((CardinalityConstraint) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public CardinalityConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasMinCard(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MINCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public long getMinCard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CardinalityConstraint) getCurrentMatch()).getMinCard();
      }
      return 0;
   }
   
   public CardinalityConstraintPO withMinCard(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setMinCard(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasMaxCard(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MAXCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public long getMaxCard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CardinalityConstraint) getCurrentMatch()).getMaxCard();
      }
      return 0;
   }
   
   public CardinalityConstraintPO withMaxCard(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setMaxCard(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MODIFIER)
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
         return ((CardinalityConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public CardinalityConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HASMATCH)
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
         return ((CardinalityConstraint) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public CardinalityConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_PATTERNOBJECTNAME)
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
         return ((CardinalityConstraint) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public CardinalityConstraintPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_DOALLMATCHES)
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
         return ((CardinalityConstraint) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public CardinalityConstraintPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CardinalityConstraint) getCurrentMatch()).setDoAllMatches(value);
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

   public CardinalityConstraintPO hasPattern(PatternPO tgt)
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
      PatternObjectPO result = new PatternObjectPO();
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(CardinalityConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public CardinalityConstraintPO hasSrc(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, CardinalityConstraint.PROPERTY_SRC);
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CardinalityConstraint) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public CardinalityConstraintPO hasTgtRoleName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasHostGraphSrcObject(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasMinCard(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MINCARD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasMaxCard(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MAXCARD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CardinalityConstraintPO createTgtRoleName(String value)
   {
      this.startCreate().hasTgtRoleName(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createHostGraphSrcObject(Object value)
   {
      this.startCreate().hasHostGraphSrcObject(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createMinCard(long value)
   {
      this.startCreate().hasMinCard(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createMaxCard(long value)
   {
      this.startCreate().hasMaxCard(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public CardinalityConstraintPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public CardinalityConstraintPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public PatternObjectPO createSrc()
   {
      return (PatternObjectPO) this.startCreate().hasSrc().endCreate();
   }

   public CardinalityConstraintPO createSrc(PatternObjectPO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public CardinalityConstraintPO filterTgtRoleName(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterTgtRoleName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterHostGraphSrcObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterMinCard(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MINCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterMinCard(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MINCARD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterMaxCard(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MAXCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterMaxCard(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MAXCARD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardinalityConstraintPO filterDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_DOALLMATCHES)
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
      super.hasLink(CardinalityConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public CardinalityConstraintPO filterSrc(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, CardinalityConstraint.PROPERTY_SRC);
   }

}



