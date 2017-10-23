package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;

public class LBoatPO extends PatternObject<LBoatPO, LBoat>
{

    public LBoatSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LBoatSet matches = new LBoatSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LBoat) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LBoatPO(){
      newInstance(null);
   }

   public LBoatPO(LBoat... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LBoatPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LBankPO createBankPO()
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBoat.PROPERTY_BANK, result);
      
      return result;
   }

   public LBankPO createBankPO(String modifier)
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBoat.PROPERTY_BANK, result);
      
      return result;
   }

   public LBoatPO createBankLink(LBankPO tgt)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_BANK);
   }

   public LBoatPO createBankLink(LBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_BANK, modifier);
   }

   public LBank getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBoat) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public LRiverPO createRiverPO()
   {
      LRiverPO result = new LRiverPO(new LRiver[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBoat.PROPERTY_RIVER, result);
      
      return result;
   }

   public LRiverPO createRiverPO(String modifier)
   {
      LRiverPO result = new LRiverPO(new LRiver[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBoat.PROPERTY_RIVER, result);
      
      return result;
   }

   public LBoatPO createRiverLink(LRiverPO tgt)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_RIVER);
   }

   public LBoatPO createRiverLink(LRiverPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_RIVER, modifier);
   }

   public LRiverSet getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBoat) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

   public CargoPO createCargoPO()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBoat.PROPERTY_CARGO, result);
      
      return result;
   }

   public CargoPO createCargoPO(String modifier)
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBoat.PROPERTY_CARGO, result);
      
      return result;
   }

   public LBoatPO createCargoLink(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_CARGO);
   }

   public LBoatPO createCargoLink(CargoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBoat.PROPERTY_CARGO, modifier);
   }

   public Cargo getCargo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBoat) this.getCurrentMatch()).getCargo();
      }
      return null;
   }

}
