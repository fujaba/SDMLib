package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.creators.RuleApplicationSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.ReachableStatePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.RuleApplicationPO;
import org.sdmlib.models.pattern.ReachableState;

public class RuleApplicationPO extends
      PatternObject<RuleApplicationPO, RuleApplication>
{
   public RuleApplicationSet allMatches()
   {
      this.setDoAllMatches(true);

      RuleApplicationSet matches = new RuleApplicationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((RuleApplication) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public RuleApplicationPO hasDescription(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(RuleApplication.PROPERTY_DESCRIPTION)
         .withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getDescription()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) getCurrentMatch()).getDescription();
      }
      return null;
   }

   public RuleApplicationPO withDescription(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((RuleApplication) getCurrentMatch()).setDescription(value);
      }
      return this;
   }

   public ReachableStatePO hasSrc()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(RuleApplication.PROPERTY_SRC, result);

      return result;
   }

   public RuleApplicationPO hasSrc(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(RuleApplication.PROPERTY_SRC)
         .withSrc(this).withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public ReachableState getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public ReachableStatePO hasTgt()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(RuleApplication.PROPERTY_TGT, result);

      return result;
   }

   public RuleApplicationPO hasTgt(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(RuleApplication.PROPERTY_TGT)
         .withSrc(this).withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public ReachableState getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

   public RuleApplicationPO hasDescription(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(RuleApplication.PROPERTY_DESCRIPTION)
         .withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public RuleApplicationPO createDescription(String value)
   {
      this.startCreate().hasDescription(value).endCreate();
      return this;
   }

   public ReachableStatePO createSrc()
   {
      return this.startCreate().hasSrc().endCreate();
   }

   public RuleApplicationPO createSrc(ReachableStatePO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public ReachableStatePO createTgt()
   {
      return this.startCreate().hasTgt().endCreate();
   }

   public RuleApplicationPO createTgt(ReachableStatePO tgt)
   {
      return this.startCreate().hasTgt(tgt).endCreate();
   }

}
