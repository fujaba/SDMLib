package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;

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
   
   public BankPO hasBank()
   {
      BankPO result = new BankPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Boat.PROPERTY_BANK, result);
      
      return result;
   }

   public BoatPO hasBank(BankPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Boat.PROPERTY_BANK)
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
         return ((Boat) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public CargoPO hasCargo()
   {
      CargoPO result = new CargoPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Boat.PROPERTY_CARGO, result);
      
      return result;
   }

   public BoatPO hasCargo(CargoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Boat.PROPERTY_CARGO)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Cargo getCargo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Boat) this.getCurrentMatch()).getCargo();
      }
      return null;
   }

   public RiverPO hasRiver()
   {
      RiverPO result = new RiverPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Boat.PROPERTY_RIVER, result);
      
      return result;
   }

   public BoatPO hasRiver(RiverPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Boat.PROPERTY_RIVER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public River getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Boat) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

}


