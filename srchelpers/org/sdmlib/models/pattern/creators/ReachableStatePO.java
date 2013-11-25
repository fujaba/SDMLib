package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.creators.ReachableStateSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.ReachabilityGraphPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.ReachableStatePO;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.creators.RuleApplicationPO;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.creators.RuleApplicationSet;

public class ReachableStatePO extends PatternObject<ReachableStatePO, ReachableState>
{
   public ReachableStateSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReachableStateSet matches = new ReachableStateSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReachableState) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ReachabilityGraphPO hasParent()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReachableStatePO hasParent(ReachabilityGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachabilityGraph getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public ReachabilityGraphPO hasMaster()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_MASTER, result);
      
      return result;
   }

   public ReachableStatePO hasMaster(ReachabilityGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_MASTER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachabilityGraph getMaster()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getMaster();
      }
      return null;
   }

   public ReachableStatePO hasGraphRoot(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_GRAPHROOT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Object getGraphRoot()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).getGraphRoot();
      }
      return null;
   }
   
   public ReachableStatePO withGraphRoot(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setGraphRoot(value);
      }
      return this;
   }
   
   public ReachableStatePO hasNumber(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public long getNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).getNumber();
      }
      return 0;
   }
   
   public ReachableStatePO withNumber(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setNumber(value);
      }
      return this;
   }
   
   public RuleApplicationPO hasRuleapplications()
   {
      RuleApplicationPO result = new RuleApplicationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_RULEAPPLICATIONS, result);
      
      return result;
   }

   public ReachableStatePO hasRuleapplications(RuleApplicationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_RULEAPPLICATIONS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public RuleApplicationSet getRuleapplications()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getRuleapplications();
      }
      return null;
   }

   public RuleApplicationPO hasResultOf()
   {
      RuleApplicationPO result = new RuleApplicationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_RESULTOF, result);
      
      return result;
   }

   public ReachableStatePO hasResultOf(RuleApplicationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_RESULTOF)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public RuleApplicationSet getResultOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getResultOf();
      }
      return null;
   }

}





