package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternObject;

public class PatternObjectPO extends PatternObject<PatternObjectPO, PatternObject>
{

   public PatternObjectSet allMatches()
   {
      this.setDoAllMatches(true);

      PatternObjectSet matches = new PatternObjectSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PatternObject) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public PatternObjectPO()
   {
      newInstance(null);
   }

   public PatternObjectPO(PatternObject... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(null, hostGraphObject);
   }

   public PatternObjectPO(String modifier)
   {
      this.setModifier(modifier);
   }

   public PatternObjectPO createCurrentMatchCondition(Object value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_CURRENTMATCH)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createCurrentMatchAssignment(Object value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_CURRENTMATCH)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(Pattern.CREATE)
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObject getCurrentMatch()
   {
      if (super.getCurrentMatch() != null)
      {
         return (PatternObject) ((PatternObject) super.getCurrentMatch()).getCurrentMatch();
      }
      return null;
   }

   public PatternObjectPO withCurrentMatch(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setCurrentMatch(value);
      }
      return this;
   }

   public PatternObjectPO createCandidatesCondition(Object value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_CANDIDATES)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createCandidatesAssignment(Object value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_CANDIDATES)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(Pattern.CREATE)
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public Object getCandidates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getCandidates();
      }
      return null;
   }

   public PatternObjectPO withCandidates(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setCandidates(value);
      }
      return this;
   }

   public PatternObjectPO createModifierCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_MODIFIER)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_MODIFIER)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_MODIFIER)
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
         return ((PatternObject) getCurrentMatch()).getModifier();
      }
      return null;
   }

   public PatternObjectPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setModifier(value);
      }
      return this;
   }

   public PatternObjectPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_HASMATCH)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_HASMATCH)
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
         return ((PatternObject) getCurrentMatch()).isHasMatch();
      }
      return false;
   }

   public PatternObjectPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }

   public PatternObjectPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
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
         return ((PatternObject) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }

   public PatternObjectPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }

   public PatternObjectPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_DOALLMATCHES)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PatternObjectPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(PatternObject.PROPERTY_DOALLMATCHES)
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
         return ((PatternObject) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }

   public PatternObjectPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }

   public PatternPO createPatternPO()
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);

      return result;
   }

   public PatternPO createPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);

      return result;
   }

   public PatternObjectPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public PatternObjectPO createPatternLink(PatternPO tgt, String modifier)
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

   public AttributeConstraintPO createAttrConstraintsPO()
   {
      AttributeConstraintPO result = new AttributeConstraintPO(new AttributeConstraint[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_ATTRCONSTRAINTS, result);

      return result;
   }

   public AttributeConstraintPO createAttrConstraintsPO(String modifier)
   {
      AttributeConstraintPO result = new AttributeConstraintPO(new AttributeConstraint[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternObject.PROPERTY_ATTRCONSTRAINTS, result);

      return result;
   }

   public PatternObjectPO createAttrConstraintsLink(AttributeConstraintPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_ATTRCONSTRAINTS);
   }

   public PatternObjectPO createAttrConstraintsLink(AttributeConstraintPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_ATTRCONSTRAINTS, modifier);
   }

   public AttributeConstraintSet getAttrConstraints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getAttrConstraints();
      }
      return null;
   }

   public DestroyObjectElemPO createDestroyElemPO()
   {
      DestroyObjectElemPO result = new DestroyObjectElemPO(new DestroyObjectElem[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_DESTROYELEM, result);

      return result;
   }

   public DestroyObjectElemPO createDestroyElemPO(String modifier)
   {
      DestroyObjectElemPO result = new DestroyObjectElemPO(new DestroyObjectElem[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternObject.PROPERTY_DESTROYELEM, result);

      return result;
   }

   public PatternObjectPO createDestroyElemLink(DestroyObjectElemPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_DESTROYELEM);
   }

   public PatternObjectPO createDestroyElemLink(DestroyObjectElemPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_DESTROYELEM, modifier);
   }

   public DestroyObjectElem getDestroyElem()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getDestroyElem();
      }
      return null;
   }

   public CardinalityConstraintPO createCardConstraintsPO()
   {
      CardinalityConstraintPO result = new CardinalityConstraintPO(new CardinalityConstraint[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_CARDCONSTRAINTS, result);

      return result;
   }

   public CardinalityConstraintPO createCardConstraintsPO(String modifier)
   {
      CardinalityConstraintPO result = new CardinalityConstraintPO(new CardinalityConstraint[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternObject.PROPERTY_CARDCONSTRAINTS, result);

      return result;
   }

   public PatternObjectPO createCardConstraintsLink(CardinalityConstraintPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_CARDCONSTRAINTS);
   }

   public PatternObjectPO createCardConstraintsLink(CardinalityConstraintPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_CARDCONSTRAINTS, modifier);
   }

   public CardinalityConstraintSet getCardConstraints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getCardConstraints();
      }
      return null;
   }

   public MatchOtherThenPO createMatchOtherThenPO()
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_MATCHOTHERTHEN, result);

      return result;
   }

   public MatchOtherThenPO createMatchOtherThenPO(String modifier)
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternObject.PROPERTY_MATCHOTHERTHEN, result);

      return result;
   }

   public PatternObjectPO createMatchOtherThenLink(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_MATCHOTHERTHEN);
   }

   public PatternObjectPO createMatchOtherThenLink(MatchOtherThenPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_MATCHOTHERTHEN, modifier);
   }

   public MatchOtherThenSet getMatchOtherThen()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getMatchOtherThen();
      }
      return null;
   }

   public MatchOtherThenPO createExcludersPO()
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_EXCLUDERS, result);

      return result;
   }

   public MatchOtherThenPO createExcludersPO(String modifier)
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternObject.PROPERTY_EXCLUDERS, result);

      return result;
   }

   public PatternObjectPO createExcludersLink(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_EXCLUDERS);
   }

   public PatternObjectPO createExcludersLink(MatchOtherThenPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_EXCLUDERS, modifier);
   }

   public MatchOtherThenSet getExcluders()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getExcluders();
      }
      return null;
   }

}
