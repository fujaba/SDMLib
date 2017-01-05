package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;

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


   public ReachableStatePO(){
      newInstance(null);
   }

   public ReachableStatePO(ReachableState... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ReachableStatePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ReachableStatePO createNumberCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createNumberCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createNumberAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public ReachableStatePO createMetricValueCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_METRICVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createMetricValueCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_METRICVALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createMetricValueAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_METRICVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getMetricValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).getMetricValue();
      }
      return 0;
   }
   
   public ReachableStatePO withMetricValue(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setMetricValue(value);
      }
      return this;
   }
   
   public ReachableStatePO createGraphRootCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_GRAPHROOT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createGraphRootAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_GRAPHROOT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public ReachabilityGraphPO createParentPO()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachableState.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReachabilityGraphPO createParentPO(String modifier)
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReachableState.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReachableStatePO createParentLink(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_PARENT);
   }

   public ReachableStatePO createParentLink(ReachabilityGraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_PARENT, modifier);
   }

   public ReachabilityGraph getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public RuleApplicationPO createRuleapplicationsPO()
   {
      RuleApplicationPO result = new RuleApplicationPO(new RuleApplication[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachableState.PROPERTY_RULEAPPLICATIONS, result);
      
      return result;
   }

   public RuleApplicationPO createRuleapplicationsPO(String modifier)
   {
      RuleApplicationPO result = new RuleApplicationPO(new RuleApplication[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReachableState.PROPERTY_RULEAPPLICATIONS, result);
      
      return result;
   }

   public ReachableStatePO createRuleapplicationsLink(RuleApplicationPO tgt)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_RULEAPPLICATIONS);
   }

   public ReachableStatePO createRuleapplicationsLink(RuleApplicationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_RULEAPPLICATIONS, modifier);
   }

   public RuleApplicationSet getRuleapplications()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getRuleapplications();
      }
      return null;
   }

   public RuleApplicationPO createResultOfPO()
   {
      RuleApplicationPO result = new RuleApplicationPO(new RuleApplication[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachableState.PROPERTY_RESULTOF, result);
      
      return result;
   }

   public RuleApplicationPO createResultOfPO(String modifier)
   {
      RuleApplicationPO result = new RuleApplicationPO(new RuleApplication[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReachableState.PROPERTY_RESULTOF, result);
      
      return result;
   }

   public ReachableStatePO createResultOfLink(RuleApplicationPO tgt)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_RESULTOF);
   }

   public ReachableStatePO createResultOfLink(RuleApplicationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachableState.PROPERTY_RESULTOF, modifier);
   }

   public RuleApplicationSet getResultOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getResultOf();
      }
      return null;
   }

   public ReachableStatePO createFailureStateCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_FAILURESTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createFailureStateAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_FAILURESTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getFailureState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).isFailureState();
      }
      return false;
   }
   
   public ReachableStatePO withFailureState(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setFailureState(value);
      }
      return this;
   }
   
   public ReachableStatePO createFinalStateCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_FINALSTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createFinalStateAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_FINALSTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getFinalState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).isFinalState();
      }
      return false;
   }
   
   public ReachableStatePO withFinalState(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setFinalState(value);
      }
      return this;
   }
   
   public ReachableStatePO createStartStateCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_STARTSTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReachableStatePO createStartStateAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReachableState.PROPERTY_STARTSTATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getStartState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) getCurrentMatch()).isStartState();
      }
      return false;
   }
   
   public ReachableStatePO withStartState(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReachableState) getCurrentMatch()).setStartState(value);
      }
      return this;
   }
   
}
