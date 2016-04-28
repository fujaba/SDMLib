package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategyPO;

public class BombermanStrategyPO extends PatternObject<BombermanStrategyPO, BombermanStrategy>
{

    public BombermanStrategySet allMatches()
   {
      this.setDoAllMatches(true);
      
      BombermanStrategySet matches = new BombermanStrategySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BombermanStrategy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BombermanStrategyPO(){
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BombermanStrategyPO(BombermanStrategy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void handleMove()
   {
      if (this.getPattern().getHasMatch())
      {
          ((BombermanStrategy) getCurrentMatch()).handleMove();
      }
   }

   public BombermanStrategyPO hasSuccessor()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BombermanStrategy.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public BombermanStrategyPO createSuccessor()
   {
      return this.startCreate().hasSuccessor().endCreate();
   }

   public BombermanStrategyPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public BombermanStrategyPO createSuccessor(BombermanStrategyPO tgt)
   {
      return this.startCreate().hasSuccessor(tgt).endCreate();
   }

   public BombermanStrategy getSuccessor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanStrategy) this.getCurrentMatch()).getSuccessor();
      }
      return null;
   }

   public BombermanStrategyPO filterSuccessor()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BombermanStrategy.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public BombermanStrategyPO filterSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public BombermanStrategyPO filterBombermanstrategy()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BombermanStrategy.PROPERTY_BOMBERMANSTRATEGY, result);
      
      return result;
   }

   public BombermanStrategyPO createBombermanstrategy()
   {
      return this.startCreate().filterBombermanstrategy().endCreate();
   }

   public BombermanStrategyPO filterBombermanstrategy(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_BOMBERMANSTRATEGY);
   }

   public BombermanStrategyPO createBombermanstrategy(BombermanStrategyPO tgt)
   {
      return this.startCreate().filterBombermanstrategy(tgt).endCreate();
   }

   public BombermanStrategy getBombermanstrategy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanStrategy) this.getCurrentMatch()).getBombermanstrategy();
      }
      return null;
   }

}
