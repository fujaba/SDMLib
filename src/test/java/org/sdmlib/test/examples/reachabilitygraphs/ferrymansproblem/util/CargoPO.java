package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;

public class CargoPO extends PatternObject<CargoPO, Cargo>
{

    public CargoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CargoSet matches = new CargoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Cargo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CargoPO(){
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CargoPO(Cargo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public CargoPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CargoPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public CargoPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cargo) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public CargoPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Cargo) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public BankPO hasBank()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public BankPO createBank()
   {
      return this.startCreate().hasBank().endCreate();
   }

   public CargoPO hasBank(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK);
   }

   public CargoPO createBank(BankPO tgt)
   {
      return this.startCreate().hasBank(tgt).endCreate();
   }

   public Bank getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cargo) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public BoatPO hasBoat()
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public BoatPO createBoat()
   {
      return this.startCreate().hasBoat().endCreate();
   }

   public CargoPO hasBoat(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT);
   }

   public CargoPO createBoat(BoatPO tgt)
   {
      return this.startCreate().hasBoat(tgt).endCreate();
   }

   public Boat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cargo) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

}
