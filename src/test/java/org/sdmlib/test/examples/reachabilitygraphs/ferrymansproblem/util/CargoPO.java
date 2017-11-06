package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPO;

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

   public CargoPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CargoPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO filterBank()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public CargoPO filterBank(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK);
   }

   public BoatPO filterBoat()
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public CargoPO filterBoat(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT);
   }


   public CargoPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CargoPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CargoPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CargoPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BoatPO createBoatPO()
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public BoatPO createBoatPO(String modifier)
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public CargoPO createBoatLink(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT);
   }

   public CargoPO createBoatLink(BoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT, modifier);
   }

   public BankPO createBankPO()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public BankPO createBankPO(String modifier)
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public CargoPO createBankLink(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK);
   }

   public CargoPO createBankLink(BankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK, modifier);
   }

}
