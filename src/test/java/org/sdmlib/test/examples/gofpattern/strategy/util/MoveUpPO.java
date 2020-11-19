package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.MoveUp;

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
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MoveUpPO(MoveUp... hostGraphObject) {
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

   public BombermanStrategyPO filterSuccessor()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BombermanStrategy.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public MoveUpPO filterSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }


   public MoveUpPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BombermanStrategyPO createSuccessorPO()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MoveUp.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public BombermanStrategyPO createSuccessorPO(String modifier)
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(modifier);
      super.hasLink(MoveUp.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public MoveUpPO createSuccessorLink(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, MoveUp.PROPERTY_SUCCESSOR);
   }

   public MoveUpPO createSuccessorLink(BombermanStrategyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MoveUp.PROPERTY_SUCCESSOR, modifier);
   }

}
