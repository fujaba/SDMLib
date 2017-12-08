package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;

public class UBoatPO extends PatternObject<UBoatPO, UBoat>
{

    public UBoatSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UBoatSet matches = new UBoatSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UBoat) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UBoatPO(){
      newInstance(null);
   }

   public UBoatPO(UBoat... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UBoatPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UBankPO createBankPO()
   {
      UBankPO result = new UBankPO(new UBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(UBoat.PROPERTY_BANK, result);
      
      return result;
   }

   public UBankPO createBankPO(String modifier)
   {
      UBankPO result = new UBankPO(new UBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(UBoat.PROPERTY_BANK, result);
      
      return result;
   }

   public UBoatPO createBankLink(UBankPO tgt)
   {
      return hasLinkConstraint(tgt, UBoat.PROPERTY_BANK);
   }

   public UBoatPO createBankLink(UBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, UBoat.PROPERTY_BANK, modifier);
   }

   public UBank getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UBoat) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public UCargoPO createCargoPO()
   {
      UCargoPO result = new UCargoPO(new UCargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(UBoat.PROPERTY_CARGO, result);
      
      return result;
   }

   public UCargoPO createCargoPO(String modifier)
   {
      UCargoPO result = new UCargoPO(new UCargo[]{});
      
      result.setModifier(modifier);
      super.hasLink(UBoat.PROPERTY_CARGO, result);
      
      return result;
   }

   public UBoatPO createCargoLink(UCargoPO tgt)
   {
      return hasLinkConstraint(tgt, UBoat.PROPERTY_CARGO);
   }

   public UBoatPO createCargoLink(UCargoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, UBoat.PROPERTY_CARGO, modifier);
   }

   public UCargo getCargo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UBoat) this.getCurrentMatch()).getCargo();
      }
      return null;
   }

}
