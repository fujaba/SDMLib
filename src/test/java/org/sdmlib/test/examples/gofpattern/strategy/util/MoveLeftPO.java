package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.MoveLeft;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategyPO;
import org.sdmlib.test.examples.gofpattern.strategy.util.MoveLeftPO;

public class MoveLeftPO extends PatternObject<MoveLeftPO, MoveLeft>
{

    public MoveLeftSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MoveLeftSet matches = new MoveLeftSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MoveLeft) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MoveLeftPO(){
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MoveLeftPO(MoveLeft... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

   public MoveLeftPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public MoveLeftPO createSuccessor(BombermanStrategyPO tgt)
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

   public MoveLeftPO filterSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

}
