package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;

public class LCargoPO extends PatternObject<LCargoPO, LCargo>
{

    public LCargoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LCargoSet matches = new LCargoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LCargo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LCargoPO(){
      newInstance(null);
   }

   public LCargoPO(LCargo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LCargoPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LCargoPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LCargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LCargoPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LCargo.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LCargoPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LCargo.PROPERTY_NAME)
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
         return ((LCargo) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LCargoPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LCargo) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public LBoatPO createBoatPO()
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LCargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public LBoatPO createBoatPO(String modifier)
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(modifier);
      super.hasLink(LCargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public LCargoPO createBoatLink(LBoatPO tgt)
   {
      return hasLinkConstraint(tgt, LCargo.PROPERTY_BOAT);
   }

   public LCargoPO createBoatLink(LBoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LCargo.PROPERTY_BOAT, modifier);
   }

   public LBoatSet getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LCargo) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public LBankPO createBankPO()
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LCargo.PROPERTY_BANK, result);
      
      return result;
   }

   public LBankPO createBankPO(String modifier)
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(LCargo.PROPERTY_BANK, result);
      
      return result;
   }

   public LCargoPO createBankLink(LBankPO tgt)
   {
      return hasLinkConstraint(tgt, LCargo.PROPERTY_BANK);
   }

   public LCargoPO createBankLink(LBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LCargo.PROPERTY_BANK, modifier);
   }

   public LBankSet getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LCargo) this.getCurrentMatch()).getBank();
      }
      return null;
   }

}
