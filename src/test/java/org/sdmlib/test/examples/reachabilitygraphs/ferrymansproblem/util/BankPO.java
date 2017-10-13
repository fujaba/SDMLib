package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverPO;

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


   public BankPO(){
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BankPO(Bank... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public BankPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BankPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
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
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BankPO hasAge(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
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
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_BOAT, result);
      
      return result;
   }

   public BoatPO createBoat()
   {
      return this.startCreate().hasBoat().endCreate();
   }

   public BankPO hasBoat(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BOAT);
   }

   public BankPO createBoat(BoatPO tgt)
   {
      return this.startCreate().hasBoat(tgt).endCreate();
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
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_RIVER, result);
      
      return result;
   }

   public RiverPO createRiver()
   {
      return this.startCreate().hasRiver().endCreate();
   }

   public BankPO hasRiver(RiverPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_RIVER);
   }

   public BankPO createRiver(RiverPO tgt)
   {
      return this.startCreate().hasRiver(tgt).endCreate();
   }

   public River getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public CargoPO hasCargos()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public CargoPO createCargos()
   {
      return this.startCreate().hasCargos().endCreate();
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public BankPO hasCargos(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_CARGOS);
   }

   public BankPO createCargos(CargoPO tgt)
   {
      return this.startCreate().hasCargos(tgt).endCreate();
   }

   public CargoSet getCargos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getCargos();
      }
      return null;
   }

   public BankPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO filterAge(int value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO filterAge(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BoatPO filterBoat()
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_BOAT, result);
      
      return result;
   }

   public BankPO filterBoat(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BOAT);
   }

   public RiverPO filterRiver()
   {
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_RIVER, result);
      
      return result;
   }

   public BankPO filterRiver(RiverPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_RIVER);
   }

   public CargoPO filterCargos()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public BankPO filterCargos(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_CARGOS);
   }


   public BankPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BankPO createAgeCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createAgeCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createAgeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_NAME)
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
      super.hasLink(Bank.PROPERTY_BOAT, result);
      
      return result;
   }

   public BoatPO createBoatPO(String modifier)
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_BOAT, result);
      
      return result;
   }

   public BankPO createBoatLink(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BOAT);
   }

   public BankPO createBoatLink(BoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BOAT, modifier);
   }

   public CargoPO createCargosPO()
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public CargoPO createCargosPO(String modifier)
   {
      CargoPO result = new CargoPO(new Cargo[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public BankPO createCargosLink(CargoPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_CARGOS);
   }

   public BankPO createCargosLink(CargoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_CARGOS, modifier);
   }

   public RiverPO createRiverPO()
   {
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_RIVER, result);
      
      return result;
   }

   public RiverPO createRiverPO(String modifier)
   {
      RiverPO result = new RiverPO(new River[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_RIVER, result);
      
      return result;
   }

   public BankPO createRiverLink(RiverPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_RIVER);
   }

   public BankPO createRiverLink(RiverPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_RIVER, modifier);
   }

}
