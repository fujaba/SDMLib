package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.creators.CardinalityConstraintSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.CardinalityConstraintPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternObjectPO;

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
   
   public CardinalityConstraintPO hasTgtRoleName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      
      super.hasLink(CardinalityConstraint.PROPERTY_SRC, result);
      
      return result;
   }

   public CardinalityConstraintPO hasSrc(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(CardinalityConstraint.PROPERTY_SRC)
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
         return ((CardinalityConstraint) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

}

