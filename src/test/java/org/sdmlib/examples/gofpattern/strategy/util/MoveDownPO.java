package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.gofpattern.strategy.MoveDown;
import org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategyPO;
import org.sdmlib.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.examples.gofpattern.strategy.util.MoveDownPO;

public class MoveDownPO extends PatternObject<MoveDownPO, MoveDown>
{

    public MoveDownSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MoveDownSet matches = new MoveDownSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MoveDown) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MoveDownPO(){
      newInstance(org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MoveDownPO(MoveDown... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

   public MoveDownPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public MoveDownPO createSuccessor(BombermanStrategyPO tgt)
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

}
