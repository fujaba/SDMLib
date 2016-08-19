package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;

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

   public ReachabilityGraphPO()
   {
      newInstance(null);
   }

   public ReachabilityGraphPO(ReachabilityGraph... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(null, hostGraphObject);
   }

   public ReachabilityGraphPO(String modifier)
   {
      this.setModifier(modifier);
   }

   public PatternPO createRulesPO()
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachabilityGraph.PROPERTY_RULES, result);

      return result;
   }

   public PatternPO createRulesPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(modifier);
      super.hasLink(ReachabilityGraph.PROPERTY_RULES, result);

      return result;
   }

   public ReachabilityGraphPO createRulesLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_RULES);
   }

   public ReachabilityGraphPO createRulesLink(PatternPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_RULES, modifier);
   }

   public PatternSet getRules()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getRules();
      }
      return null;
   }

   public ReachableStatePO createFinalStatesPO()
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachabilityGraph.PROPERTY_FINALSTATES, result);

      return result;
   }

   public ReachableStatePO createFinalStatesPO(String modifier)
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(modifier);
      super.hasLink(ReachabilityGraph.PROPERTY_FINALSTATES, result);

      return result;
   }

   public ReachabilityGraphPO createFinalStatesLink(ReachableStatePO tgt)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_FINALSTATES);
   }

   public ReachabilityGraphPO createFinalStatesLink(ReachableStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_FINALSTATES, modifier);
   }

   public ReachableStateSet getFinalStates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getFinalStates();
      }
      return null;
   }

   public ReachableStatePO createStatesPO()
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachabilityGraph.PROPERTY_STATES, result);

      return result;
   }

   public ReachableStatePO createStatesPO(String modifier)
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(modifier);
      super.hasLink(ReachabilityGraph.PROPERTY_STATES, result);

      return result;
   }

   public ReachabilityGraphPO createStatesLink(ReachableStatePO tgt)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_STATES);
   }

   public ReachabilityGraphPO createStatesLink(ReachableStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_STATES, modifier);
   }

   public ReachableStateSet getStates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachabilityGraph) this.getCurrentMatch()).getStates();
      }
      return null;
   }

   public ReachableStatePO createTodoPO()
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReachabilityGraph.PROPERTY_TODO, result);

      return result;
   }

   public ReachableStatePO createTodoPO(String modifier)
   {
      ReachableStatePO result = new ReachableStatePO(new ReachableState[]
      {});

      result.setModifier(modifier);
      super.hasLink(ReachabilityGraph.PROPERTY_TODO, result);

      return result;
   }

   public ReachabilityGraphPO createTodoLink(ReachableStatePO tgt)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_TODO);
   }

   public ReachabilityGraphPO createTodoLink(ReachableStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReachabilityGraph.PROPERTY_TODO, modifier);
   }

   public ReachableStateSet getTodo()
   {
      if (this.getPattern().getHasMatch())
      {
         return new ReachableStateSet(((ReachabilityGraph) this.getCurrentMatch()).getTodo());
      }
      return null;
   }

}
