package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.CardinalityConstraintPO;
import org.sdmlib.models.pattern.util.PatternObjectPO;

public class CardinalityConstraintPO extends PatternObject<CardinalityConstraintPO, CardinalityConstraint>
{

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


   public CardinalityConstraintPO(){
      newInstance(null);
   }

   public CardinalityConstraintPO(CardinalityConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CardinalityConstraintPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CardinalityConstraintPO createTgtRoleNameCondition(String value)
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
   
   public CardinalityConstraintPO createTgtRoleNameCondition(String lower, String upper)
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
   
   public CardinalityConstraintPO createTgtRoleNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public CardinalityConstraintPO createHostGraphSrcObjectCondition(Object value)
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
   
   public CardinalityConstraintPO createHostGraphSrcObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
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
   
   public CardinalityConstraintPO createMinCardCondition(long value)
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
   
   public CardinalityConstraintPO createMinCardCondition(long lower, long upper)
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
   
   public CardinalityConstraintPO createMinCardAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MINCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public CardinalityConstraintPO createMaxCardCondition(long value)
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
   
   public CardinalityConstraintPO createMaxCardCondition(long lower, long upper)
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
   
   public CardinalityConstraintPO createMaxCardAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MAXCARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public CardinalityConstraintPO createModifierCondition(String value)
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
   
   public CardinalityConstraintPO createModifierCondition(String lower, String upper)
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
   
   public CardinalityConstraintPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_MODIFIER)
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
   
   public CardinalityConstraintPO createHasMatchCondition(boolean value)
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
   
   public CardinalityConstraintPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_HASMATCH)
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
         return ((CardinalityConstraint) getCurrentMatch()).isHasMatch();
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
   
   public CardinalityConstraintPO createPatternObjectNameCondition(String value)
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
   
   public CardinalityConstraintPO createPatternObjectNameCondition(String lower, String upper)
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
   
   public CardinalityConstraintPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_PATTERNOBJECTNAME)
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
   
   public CardinalityConstraintPO createDoAllMatchesCondition(boolean value)
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
   
   public CardinalityConstraintPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(CardinalityConstraint.PROPERTY_DOALLMATCHES)
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
         return ((CardinalityConstraint) getCurrentMatch()).isDoAllMatches();
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

   public CardinalityConstraintPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public CardinalityConstraintPO createPatternLink(PatternPO tgt, String modifier)
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
      super.hasLink(CardinalityConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public PatternObjectPO createSrcPO(String modifier)
   {
      PatternObjectPO result = new PatternObjectPO(new PatternObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(CardinalityConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public CardinalityConstraintPO createSrcLink(PatternObjectPO tgt)
   {
      return hasLinkConstraint(tgt, CardinalityConstraint.PROPERTY_SRC);
   }

   public CardinalityConstraintPO createSrcLink(PatternObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, CardinalityConstraint.PROPERTY_SRC, modifier);
   }

   public PatternObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CardinalityConstraint) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

}
