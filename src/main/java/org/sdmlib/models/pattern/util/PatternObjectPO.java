package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;
import org.sdmlib.models.pattern.util.CardinalityConstraintPO;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.util.PatternObjectPO;
import org.sdmlib.models.pattern.util.MatchOtherThenPO;
import org.sdmlib.models.pattern.MatchOtherThen;


public class PatternObjectPO extends PatternObject<PatternObjectPO, PatternObject>
{
   public PatternObjectPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PatternObjectPO(PatternObject... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public PatternObjectPO hasCurrentMatch(Object value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CURRENTMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withCurrentMatch(Object value)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withCurrentMatch(value);
      }
      return this;
   }
   
   public PatternObjectPO hasCandidates(Object value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CANDIDATES)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withCandidates(Object value)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withCandidates(value);
      }
      return this;
   }
   
   public PatternObjectPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withModifier(String value)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternObjectPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withHasMatch(boolean value)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternLinkPO hasIncomming()
   {
      PatternLinkPO result = new PatternLinkPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_INCOMMING)
      .withSrc(this);
      
      super.getPattern().addToElements(patternLink);
      
      super.getPattern().addToElements(result);
      
      super.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasIncomming(PatternLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_INCOMMING)
      .withSrc(this);
      
      super.getPattern().addToElements(patternLink);
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withIncomming(PatternLinkPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withIncomming((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutIncomming(PatternLinkPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withoutIncomming((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternLinkPO hasOutgoing()
   {
      PatternLinkPO result = new PatternLinkPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_OUTGOING)
      .withSrc(this);
      
      super.getPattern().addToElements(patternLink);
      
      super.getPattern().addToElements(result);
      
      super.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasOutgoing(PatternLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_OUTGOING)
      .withSrc(this);
      
      super.getPattern().addToElements(patternLink);
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withOutgoing(PatternLinkPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withOutgoing((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutOutgoing(PatternLinkPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withoutOutgoing((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public AttributeConstraintPO hasAttrConstraints()
   {
      AttributeConstraintPO result = new AttributeConstraintPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_ATTRCONSTRAINTS)
      .withSrc(this);
      
      super.getPattern().addToElements(patternLink);
      
      super.getPattern().addToElements(result);
      
      super.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasAttrConstraints(AttributeConstraintPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_ATTRCONSTRAINTS);
   }
   
   public PatternObjectPO withAttrConstraints(AttributeConstraintPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withAttrConstraints((AttributeConstraint) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutAttrConstraints(AttributeConstraintPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((PatternObject) super.getCurrentMatch()).withoutAttrConstraints((AttributeConstraint) tgtPO.getCurrentMatch());
      }
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
   
   public Object getCandidates()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getCandidates();
      }
      return null;
   }
   
   public String getModifier()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
//   public PatternLinkSet getIncomming()
//   {
//      if (super.getCurrentMatch() != null)
//      {
//         return ((PatternObject) this.getCurrentMatch()).getIncomming();
//      }
//      return null;
//   }
   
//   public PatternLinkSet getOutgoing()
//   {
//      if (super.getCurrentMatch() != null)
//      {
//         return ((PatternObject) this.getCurrentMatch()).getOutgoing();
//      }
//      return null;
//   }
   
   public AttributeConstraintSet getAttrConstraints()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getAttrConstraints();
      }
      return null;
   }
   
   public DestroyObjectElemPO hasDestroyElem()
   {
      DestroyObjectElemPO result = new DestroyObjectElemPO();
      result.setModifier(super.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_DESTROYELEM, result);
      
      return result;
   }
   public PatternObjectPO hasDestroyElem(DestroyObjectElemPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_DESTROYELEM);
   }
   
   public DestroyObjectElem getDestroyElem()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getDestroyElem();
      }
      return null;
   }
   
   public PatternObjectPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternObjectPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public String getPatternObjectName()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(super.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public PatternObjectPO hasPattern(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public Pattern getPattern()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternElement) super.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }

   public CardinalityConstraintPO hasCardConstraints()
   {
      CardinalityConstraintPO result = new CardinalityConstraintPO();
      result.setModifier(super.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_CARDCONSTRAINTS, result);
      
      return result;
   }

   public PatternObjectPO hasCardConstraints(CardinalityConstraintPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_CARDCONSTRAINTS);
   }

   public CardinalityConstraintSet getCardConstraints()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getCardConstraints();
      }
      return null;
   }

   public MatchOtherThenPO hasMatchOtherThen()
   {
      MatchOtherThenPO result = new MatchOtherThenPO();
      result.setModifier(super.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_MATCHOTHERTHEN, result);
      
      return result;
   }

   public PatternObjectPO hasMatchOtherThen(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_MATCHOTHERTHEN);
   }

   public MatchOtherThenSet getMatchOtherThen()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getMatchOtherThen();
      }
      return null;
   }

   public MatchOtherThenPO hasExcluders()
   {
      MatchOtherThenPO result = new MatchOtherThenPO();
      result.setModifier(super.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_EXCLUDERS, result);
      
      return result;
   }

   public PatternObjectPO hasExcluders(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_EXCLUDERS);
   }

   public MatchOtherThenSet getExcluders()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternObject) super.getCurrentMatch()).getExcluders();
      }
      return null;
   }

//   public PatternLinkSet getOutgoing()
//   {
//      if (super.getCurrentMatch() != null)
//      {
//         return ((PatternObject) this.getCurrentMatch()).getOutgoing();
//      }
//      return null;
//   }


   public PatternObjectPO hasCurrentMatch(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CURRENTMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO hasCandidates(Object lower, Object upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CANDIDATES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(super.getPattern().getModifier())
      .withPattern(super.getPattern());
      
      super.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO createCurrentMatch(Object value)
   {
      this.startCreate().hasCurrentMatch(value).endCreate();
      return this;
   }
   
   public PatternObjectPO createCandidates(Object value)
   {
      this.startCreate().hasCandidates(value).endCreate();
      return this;
   }
   
   public PatternObjectPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public PatternObjectPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public PatternObjectPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public PatternObjectPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public PatternObjectPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public AttributeConstraintPO createAttrConstraints()
   {
      return this.startCreate().hasAttrConstraints().endCreate();
   }

   public PatternObjectPO createAttrConstraints(AttributeConstraintPO tgt)
   {
      return this.startCreate().hasAttrConstraints(tgt).endCreate();
   }

   public DestroyObjectElemPO createDestroyElem()
   {
      return this.startCreate().hasDestroyElem().endCreate();
   }

   public PatternObjectPO createDestroyElem(DestroyObjectElemPO tgt)
   {
      return this.startCreate().hasDestroyElem(tgt).endCreate();
   }

   public CardinalityConstraintPO createCardConstraints()
   {
      return this.startCreate().hasCardConstraints().endCreate();
   }

   public PatternObjectPO createCardConstraints(CardinalityConstraintPO tgt)
   {
      return this.startCreate().hasCardConstraints(tgt).endCreate();
   }

   public MatchOtherThenPO createMatchOtherThen()
   {
      return this.startCreate().hasMatchOtherThen().endCreate();
   }

   public PatternObjectPO createMatchOtherThen(MatchOtherThenPO tgt)
   {
      return this.startCreate().hasMatchOtherThen(tgt).endCreate();
   }

   public MatchOtherThenPO createExcluders()
   {
      return this.startCreate().hasExcluders().endCreate();
   }

   public PatternObjectPO createExcluders(MatchOtherThenPO tgt)
   {
      return this.startCreate().hasExcluders(tgt).endCreate();
   }

   public PatternObjectPO filterCurrentMatch(Object value)
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
   
   public PatternObjectPO filterCandidates(Object value)
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
   
   public PatternObjectPO filterModifier(String value)
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
   
   public PatternObjectPO filterModifier(String lower, String upper)
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
   
   public PatternObjectPO filterHasMatch(boolean value)
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
   
   public PatternObjectPO filterPatternObjectName(String value)
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
   
   public PatternObjectPO filterPatternObjectName(String lower, String upper)
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
   
   public PatternObjectPO filterDoAllMatches(boolean value)
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
   
   public CardinalityConstraintPO filterCardConstraints()
   {
      CardinalityConstraintPO result = new CardinalityConstraintPO(new CardinalityConstraint[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_CARDCONSTRAINTS, result);
      
      return result;
   }

   public PatternObjectPO filterCardConstraints(CardinalityConstraintPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_CARDCONSTRAINTS);
   }

   public MatchOtherThenPO filterMatchOtherThen()
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_MATCHOTHERTHEN, result);
      
      return result;
   }

   public PatternObjectPO filterMatchOtherThen(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_MATCHOTHERTHEN);
   }

   public MatchOtherThenPO filterExcluders()
   {
      MatchOtherThenPO result = new MatchOtherThenPO(new MatchOtherThen[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternObject.PROPERTY_EXCLUDERS, result);
      
      return result;
   }

   public PatternObjectPO filterExcluders(MatchOtherThenPO tgt)
   {
      return hasLinkConstraint(tgt, PatternObject.PROPERTY_EXCLUDERS);
   }

}


















