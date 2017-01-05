package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

public class BoatPO extends PatternObject<BoatPO, Boat>
{

    public BoatSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BoatSet matches = new BoatSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Boat) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BoatPO(){
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BoatPO(Boat... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public RiverPO hasRiver()
   {
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_RIVER, result);
      
      return result;
   }

   public RiverPO createRiver()
   {
      return this.startCreate().hasRiver().endCreate();
   }

   public BoatPO hasRiver(RiverPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_RIVER);
   }

   public BoatPO createRiver(RiverPO tgt)
   {
      return this.startCreate().hasRiver(tgt).endCreate();
   }

   public River getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Boat) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

   public BankPO hasBank()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_BANK, result);
      
      return result;
   }

   public BankPO createBank()
   {
      return this.startCreate().hasBank().endCreate();
   }

   public BoatPO hasBank(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_BANK);
   }

   public BoatPO createBank(BankPO tgt)
   {
      return this.startCreate().hasBank(tgt).endCreate();
   }

   public Bank getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Boat) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public CargoPO hasCargo()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_CARGO, result);
      
      return result;
   }

   public CargoPO createCargo()
   {
      return this.startCreate().hasCargo().endCreate();
   }

   public BoatPO hasCargo(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_CARGO);
   }

   public BoatPO createCargo(CargoPO tgt)
   {
      return this.startCreate().hasCargo(tgt).endCreate();
   }

   public Cargo getCargo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Boat) this.getCurrentMatch()).getCargo();
      }
      return null;
   }

   public RiverPO filterRiver()
   {
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_RIVER, result);
      
      return result;
   }

   public BoatPO filterRiver(RiverPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_RIVER);
   }

   public BankPO filterBank()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_BANK, result);
      
      return result;
   }

   public BoatPO filterBank(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_BANK);
   }

   public CargoPO filterCargo()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Boat.PROPERTY_CARGO, result);
      
      return result;
   }

   public BoatPO filterCargo(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, Boat.PROPERTY_CARGO);
   }

}
