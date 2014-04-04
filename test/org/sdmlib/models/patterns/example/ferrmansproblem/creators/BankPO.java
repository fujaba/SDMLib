package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;

public class BankPO extends PatternObject<BankPO, Bank>
{
   public BankSet allMatches()
   {
      this.setDoAllMatches(true);

      BankSet matches = new BankSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Bank) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public BankPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Bank.PROPERTY_NAME).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) getCurrentMatch()).getName();
      }
      return null;
   }

   public BankPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Bank) getCurrentMatch()).setName(value);
      }
      return this;
   }

   public BankPO hasAge(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Bank.PROPERTY_AGE).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public int getAge()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) getCurrentMatch()).getAge();
      }
      return 0;
   }

   public BankPO withAge(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Bank) getCurrentMatch()).setAge(value);
      }
      return this;
   }

   public BoatPO hasBoat()
   {
      BoatPO result = new BoatPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Bank.PROPERTY_BOAT, result);

      return result;
   }

   public BankPO hasBoat(BoatPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Bank.PROPERTY_BOAT).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Boat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public RiverPO hasRiver()
   {
      RiverPO result = new RiverPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Bank.PROPERTY_RIVER, result);

      return result;
   }

   public BankPO hasRiver(RiverPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Bank.PROPERTY_RIVER).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public River getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

   public CargoPO hasCargos()
   {
      CargoPO result = new CargoPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Bank.PROPERTY_CARGOS, result);

      return result;
   }

   public BankPO hasCargos(CargoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Bank.PROPERTY_CARGOS).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public CargoSet getCargos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getCargos();
      }
      return null;
   }

   public BankPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Bank.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public BankPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public BankPO hasAge(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Bank.PROPERTY_AGE).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public BankPO createAge(int value)
   {
      this.startCreate().hasAge(value).endCreate();
      return this;
   }

   public BoatPO createBoat()
   {
      return this.startCreate().hasBoat().endCreate();
   }

   public BankPO createBoat(BoatPO tgt)
   {
      return this.startCreate().hasBoat(tgt).endCreate();
   }

   public RiverPO createRiver()
   {
      return this.startCreate().hasRiver().endCreate();
   }

   public BankPO createRiver(RiverPO tgt)
   {
      return this.startCreate().hasRiver(tgt).endCreate();
   }

   public CargoPO createCargos()
   {
      return this.startCreate().hasCargos().endCreate();
   }

   public BankPO createCargos(CargoPO tgt)
   {
      return this.startCreate().hasCargos(tgt).endCreate();
   }

}
