package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Executor;
import org.sdmlib.replication.creators.ExecutorSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.StepPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.ExecutorPO;
import org.sdmlib.replication.Step;

public class ExecutorPO extends PatternObject<ExecutorPO, Executor>
{
   public ExecutorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ExecutorSet matches = new ExecutorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Executor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StepPO hasStep()
   {
      StepPO result = new StepPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Executor.PROPERTY_STEP, result);
      
      return result;
   }

   public ExecutorPO hasStep(StepPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Executor.PROPERTY_STEP)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Step getStep()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Executor) this.getCurrentMatch()).getStep();
      }
      return null;
   }

}

