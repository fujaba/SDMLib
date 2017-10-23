package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;

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
      newInstance(null);
   }

   public CargoPO(Cargo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
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
   
   public LBoatPO createBoatPO()
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public LBoatPO createBoatPO(String modifier)
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cargo.PROPERTY_BOAT, result);
      
      return result;
   }

   public CargoPO createBoatLink(LBoatPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT);
   }

   public CargoPO createBoatLink(LBoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BOAT, modifier);
   }

   public LBoatSet getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cargo) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public LBankPO createBankPO()
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public LBankPO createBankPO(String modifier)
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cargo.PROPERTY_BANK, result);
      
      return result;
   }

   public CargoPO createBankLink(LBankPO tgt)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK);
   }

   public CargoPO createBankLink(LBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cargo.PROPERTY_BANK, modifier);
   }

   public LBankSet getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cargo) this.getCurrentMatch()).getBank();
      }
      return null;
   }

}
