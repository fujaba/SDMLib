package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LCargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;

public class LBankPO extends PatternObject<LBankPO, LBank>
{

    public LBankSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LBankSet matches = new LBankSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LBank) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LBankPO(){
      newInstance(null);
   }

   public LBankPO(LBank... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LBankPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LBankPO createAgeCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LBankPO createAgeCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LBankPO createAgeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getAge()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBank) getCurrentMatch()).getAge();
      }
      return 0;
   }
   
   public LBankPO withAge(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LBank) getCurrentMatch()).setAge(value);
      }
      return this;
   }
   
   public LBankPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LBankPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LBankPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LBank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBank) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LBankPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LBank) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public LBoatPO createBoatPO()
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBank.PROPERTY_BOAT, result);
      
      return result;
   }

   public LBoatPO createBoatPO(String modifier)
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBank.PROPERTY_BOAT, result);
      
      return result;
   }

   public LBankPO createBoatLink(LBoatPO tgt)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_BOAT);
   }

   public LBankPO createBoatLink(LBoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_BOAT, modifier);
   }

   public LBoatSet getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBank) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LCargoPO createCargosPO()
   {
      LCargoPO result = new LCargoPO(new LCargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBank.PROPERTY_CARGOS, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LCargoPO createCargosPO(String modifier)
   {
      LCargoPO result = new LCargoPO(new LCargo[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBank.PROPERTY_CARGOS, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LBankPO createCargosLink(LCargoPO tgt)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_CARGOS);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LBankPO createCargosLink(LCargoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_CARGOS, modifier);
   }

   public LCargoSet getCargos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBank) this.getCurrentMatch()).getCargos();
      }
      return null;
   }

   public LRiverPO createRiverPO()
   {
      LRiverPO result = new LRiverPO(new LRiver[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LBank.PROPERTY_RIVER, result);
      
      return result;
   }

   public LRiverPO createRiverPO(String modifier)
   {
      LRiverPO result = new LRiverPO(new LRiver[]{});
      
      result.setModifier(modifier);
      super.hasLink(LBank.PROPERTY_RIVER, result);
      
      return result;
   }

   public LBankPO createRiverLink(LRiverPO tgt)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_RIVER);
   }

   public LBankPO createRiverLink(LRiverPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LBank.PROPERTY_RIVER, modifier);
   }

   public LRiverSet getRiver()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LBank) this.getCurrentMatch()).getRiver();
      }
      return null;
   }

}
