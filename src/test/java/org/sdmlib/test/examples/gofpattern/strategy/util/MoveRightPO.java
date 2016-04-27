package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.MoveRight;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategyPO;
import org.sdmlib.test.examples.gofpattern.strategy.util.MoveRightPO;

public class MoveRightPO extends PatternObject<MoveRightPO, MoveRight>
{

    public MoveRightSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MoveRightSet matches = new MoveRightSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MoveRight) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MoveRightPO(){
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MoveRightPO(MoveRight... hostGraphObject) {
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

   public MoveRightPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public MoveRightPO createSuccessor(BombermanStrategyPO tgt)
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

   public MoveRightPO filterSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

}
