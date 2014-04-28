package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }

   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public RiverPO hasElementRiverPO()
   {
      RiverPO value = new RiverPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public RiverPO hasElementRiverPO(River hostGraphObject)
   {
      RiverPO value = new RiverPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

   public BoatPO hasElementBoatPO()
   {
      BoatPO value = new BoatPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public BoatPO hasElementBoatPO(Boat hostGraphObject)
   {
      BoatPO value = new BoatPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

   public BankPO hasElementBankPO()
   {
      BankPO value = new BankPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public BankPO hasElementBankPO(Bank hostGraphObject)
   {
      BankPO value = new BankPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

   public CargoPO hasElementCargoPO()
   {
      CargoPO value = new CargoPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public CargoPO hasElementCargoPO(Cargo hostGraphObject)
   {
      CargoPO value = new CargoPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

}
