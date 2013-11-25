package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;

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
   
   public CargoPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Cargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      BankPO result = new BankPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public CargoPO hasBank(BankPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Cargo.PROPERTY_BANK)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      BoatPO result = new BoatPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public CargoPO hasBoat(BoatPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Cargo.PROPERTY_BOAT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

