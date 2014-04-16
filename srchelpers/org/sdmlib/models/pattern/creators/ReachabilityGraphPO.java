package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class ReachabilityGraphPO extends PatternObject<ReachabilityGraphPO, ReachabilityGraph>
{
   public ReachabilityGraphSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReachabilityGraphSet matches = new ReachabilityGraphSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReachabilityGraph) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ReachableStatePO hasStates()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachabilityGraph.PROPERTY_STATES, result);
      
      return result;
   }

   public ReachabilityGraphPO hasStates(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachabilityGraph.PROPERTY_STATES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachableStateSet getStates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getStates();
      }
      return null;
   }

   public ReachableStatePO hasTodo()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachabilityGraph.PROPERTY_TODO, result);
      
      return result;
   }

   public ReachabilityGraphPO hasTodo(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachabilityGraph.PROPERTY_TODO)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachableStateSet getTodo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getTodo();
      }
      return null;
   }

   public PatternPO hasRules()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachabilityGraph.PROPERTY_RULES, result);
      
      return result;
   }

   public ReachabilityGraphPO hasRules(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachabilityGraph.PROPERTY_RULES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PatternSet getRules()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getRules();
      }
      return null;
   }

   public ReachableStatePO createStates()
   {
      return this.startCreate().hasStates().endCreate();
   }

   public ReachabilityGraphPO createStates(ReachableStatePO tgt)
   {
      return this.startCreate().hasStates(tgt).endCreate();
   }

   public ReachableStatePO createTodo()
   {
      return this.startCreate().hasTodo().endCreate();
   }

   public ReachabilityGraphPO createTodo(ReachableStatePO tgt)
   {
      return this.startCreate().hasTodo(tgt).endCreate();
   }

   public PatternPO createRules()
   {
      return this.startCreate().hasRules().endCreate();
   }

   public ReachabilityGraphPO createRules(PatternPO tgt)
   {
      return this.startCreate().hasRules(tgt).endCreate();
   }

}




