package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
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


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public RiverPO(){
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public RiverPO(River... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public BoatPO hasBoat()
   {
      BoatPO result = new BoatPO(new Boat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(River.PROPERTY_BOAT, result);
      
      return result;
   }

   public BoatPO createBoat()
   {
      return this.startCreate().hasBoat().endCreate();
   }

   public RiverPO hasBoat(BoatPO tgt)
   {
      return hasLinkConstraint(tgt, River.PROPERTY_BOAT);
   }

   public RiverPO createBoat(BoatPO tgt)
   {
      return this.startCreate().hasBoat(tgt).endCreate();
   }

   public Boat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((River) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public BankPO hasBanks()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(River.PROPERTY_BANKS, result);
      
      return result;
   }

   public BankPO createBanks()
   {
      return this.startCreate().hasBanks().endCreate();
   }

   public RiverPO hasBanks(BankPO tgt)
   {
      return hasLinkConstraint(tgt, River.PROPERTY_BANKS);
   }

   public RiverPO createBanks(BankPO tgt)
   {
      return this.startCreate().hasBanks(tgt).endCreate();
   }

   public BankSet getBanks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((River) this.getCurrentMatch()).getBanks();
      }
      return null;
   }

}
