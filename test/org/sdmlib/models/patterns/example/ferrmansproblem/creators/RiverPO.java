package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;

public class RiverPO extends PatternObject<RiverPO, River>
{
   public RiverSet allMatches()
   {
      this.setDoAllMatches(true);

      RiverSet matches = new RiverSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((River) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public BankPO hasBanks()
   {
      BankPO result = new BankPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(River.PROPERTY_BANKS, result);

      return result;
   }

   public RiverPO hasBanks(BankPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(River.PROPERTY_BANKS).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public BankSet getBanks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((River) this.getCurrentMatch()).getBanks();
      }
      return null;
   }

   public BoatPO hasBoat()
   {
      BoatPO result = new BoatPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(River.PROPERTY_BOAT, result);

      return result;
   }

   public RiverPO hasBoat(BoatPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(River.PROPERTY_BOAT).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Boat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((River) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public BoatPO createBoat()
   {
      return this.startCreate().hasBoat().endCreate();
   }

   public RiverPO createBoat(BoatPO tgt)
   {
      return this.startCreate().hasBoat(tgt).endCreate();
   }

   public BankPO createBanks()
   {
      return this.startCreate().hasBanks().endCreate();
   }

   public RiverPO createBanks(BankPO tgt)
   {
      return this.startCreate().hasBanks(tgt).endCreate();
   }

}
