package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.gofpattern.strategy.MoveUp;
import org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategyPO;
import org.sdmlib.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.examples.gofpattern.strategy.util.MoveUpPO;

public class MoveUpPO extends PatternObject<MoveUpPO, MoveUp>
{

    public MoveUpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MoveUpSet matches = new MoveUpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MoveUp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MoveUpPO(){
      newInstance(org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MoveUpPO(MoveUp... hostGraphObject) {
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

   public MoveUpPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public MoveUpPO createSuccessor(BombermanStrategyPO tgt)
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
