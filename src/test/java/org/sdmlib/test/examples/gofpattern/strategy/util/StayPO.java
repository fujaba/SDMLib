package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.Stay;

public class StayPO extends PatternObject<StayPO, Stay>
{

    public StaySet allMatches()
   {
      this.setDoAllMatches(true);
      
      StaySet matches = new StaySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Stay) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StayPO(){
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StayPO(Stay... hostGraphObject) {
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

   public StayPO hasSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }

   public StayPO createSuccessor(BombermanStrategyPO tgt)
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

   public StayPO filterSuccessor(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, BombermanStrategy.PROPERTY_SUCCESSOR);
   }


   public StayPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BombermanStrategyPO createSuccessorPO()
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Stay.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public BombermanStrategyPO createSuccessorPO(String modifier)
   {
      BombermanStrategyPO result = new BombermanStrategyPO(new BombermanStrategy[]{});
      
      result.setModifier(modifier);
      super.hasLink(Stay.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public StayPO createSuccessorLink(BombermanStrategyPO tgt)
   {
      return hasLinkConstraint(tgt, Stay.PROPERTY_SUCCESSOR);
   }

   public StayPO createSuccessorLink(BombermanStrategyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Stay.PROPERTY_SUCCESSOR, modifier);
   }

}
