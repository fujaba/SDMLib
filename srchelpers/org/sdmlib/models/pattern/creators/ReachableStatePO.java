package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.creators.ReachableStateSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.ReachabilityGraphPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.ReachableStatePO;
import org.sdmlib.models.pattern.ReachabilityGraph;

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

   public ReachableStatePO hasSuccessor()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public ReachableStatePO hasSuccessor(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_SUCCESSOR)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachableStateSet getSuccessor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getSuccessor();
      }
      return null;
   }

   public ReachableStatePO hasPredecessor()
   {
      ReachableStatePO result = new ReachableStatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReachableState.PROPERTY_PREDECESSOR, result);
      
      return result;
   }

   public ReachableStatePO hasPredecessor(ReachableStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReachableState.PROPERTY_PREDECESSOR)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachableStateSet getPredecessor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReachableState) this.getCurrentMatch()).getPredecessor();
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

}


