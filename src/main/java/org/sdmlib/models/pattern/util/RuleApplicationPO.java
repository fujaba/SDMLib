package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;

public class RuleApplicationPO extends PatternObject<RuleApplicationPO, RuleApplication>
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


   public RuleApplicationPO(){
      newInstance(null);
   }

   public RuleApplicationPO(RuleApplication... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public RuleApplicationPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public RuleApplicationPO createDescriptionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(RuleApplication.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RuleApplicationPO createDescriptionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(RuleApplication.PROPERTY_DESCRIPTION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RuleApplicationPO createDescriptionAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(RuleApplication.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public PatternPO createRulePO()
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(RuleApplication.PROPERTY_RULE, result);
      
      return result;
   }

   public PatternPO createRulePO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(modifier);
      super.hasLink(RuleApplication.PROPERTY_RULE, result);
      
      return result;
   }

   public RuleApplicationPO createRuleLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_RULE);
   }

   public RuleApplicationPO createRuleLink(PatternPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_RULE, modifier);
   }

   public Pattern getRule()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) this.getCurrentMatch()).getRule();
      }
      return null;
   }

   public ReachableStatePO createSrcPO()
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(RuleApplication.PROPERTY_SRC, result);
      
      return result;
   }

   public ReachableStatePO createSrcPO(String modifier)
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]{});
      
      result.setModifier(modifier);
      super.hasLink(RuleApplication.PROPERTY_SRC, result);
      
      return result;
   }

   public RuleApplicationPO createSrcLink(ReachableStatePO tgt)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_SRC);
   }

   public RuleApplicationPO createSrcLink(ReachableStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_SRC, modifier);
   }

   public ReachableState getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public ReachableStatePO createTgtPO()
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(RuleApplication.PROPERTY_TGT, result);
      
      return result;
   }

   public ReachableStatePO createTgtPO(String modifier)
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]{});
      
      result.setModifier(modifier);
      super.hasLink(RuleApplication.PROPERTY_TGT, result);
      
      return result;
   }

   public RuleApplicationPO createTgtLink(ReachableStatePO tgt)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_TGT);
   }

   public RuleApplicationPO createTgtLink(ReachableStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, RuleApplication.PROPERTY_TGT, modifier);
   }

   public ReachableState getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RuleApplication) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

}
