package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.URiver;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.URiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;

public class URiverPO extends PatternObject<URiverPO, URiver>
{

    public URiverSet allMatches()
   {
      this.setDoAllMatches(true);
      
      URiverSet matches = new URiverSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((URiver) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public URiverPO(){
      newInstance(null);
   }

   public URiverPO(URiver... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public URiverPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UBankPO createBanksPO()
   {
      UBankPO result = new UBankPO(new UBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(URiver.PROPERTY_BANKS, result);
      
      return result;
   }

   public UBankPO createBanksPO(String modifier)
   {
      UBankPO result = new UBankPO(new UBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(URiver.PROPERTY_BANKS, result);
      
      return result;
   }

   public URiverPO createBanksLink(UBankPO tgt)
   {
      return hasLinkConstraint(tgt, URiver.PROPERTY_BANKS);
   }

   public URiverPO createBanksLink(UBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, URiver.PROPERTY_BANKS, modifier);
   }

   public UBankSet getBanks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((URiver) this.getCurrentMatch()).getBanks();
      }
      return null;
   }

   public UBoatPO createBoatPO()
   {
      UBoatPO result = new UBoatPO(new UBoat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(URiver.PROPERTY_BOAT, result);
      
      return result;
   }

   public UBoatPO createBoatPO(String modifier)
   {
      UBoatPO result = new UBoatPO(new UBoat[]{});
      
      result.setModifier(modifier);
      super.hasLink(URiver.PROPERTY_BOAT, result);
      
      return result;
   }

   public URiverPO createBoatLink(UBoatPO tgt)
   {
      return hasLinkConstraint(tgt, URiver.PROPERTY_BOAT);
   }

   public URiverPO createBoatLink(UBoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, URiver.PROPERTY_BOAT, modifier);
   }

   public UBoat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((URiver) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

}
