package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public class LRiverPO extends PatternObject<LRiverPO, LRiver>
{

    public LRiverSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LRiverSet matches = new LRiverSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LRiver) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverPO(){
      newInstance(null);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverPO(LRiver... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LBoatPO createBoatPO()
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LRiver.PROPERTY_BOAT, result);
      
      return result;
   }

   public LBoatPO createBoatPO(String modifier)
   {
      LBoatPO result = new LBoatPO(new LBoat[]{});
      
      result.setModifier(modifier);
      super.hasLink(LRiver.PROPERTY_BOAT, result);
      
      return result;
   }

   public LRiverPO createBoatLink(LBoatPO tgt)
   {
      return hasLinkConstraint(tgt, LRiver.PROPERTY_BOAT);
   }

   public LRiverPO createBoatLink(LBoatPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LRiver.PROPERTY_BOAT, modifier);
   }

   public LBoat getBoat()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LRiver) this.getCurrentMatch()).getBoat();
      }
      return null;
   }

   public LBankPO createBanksPO()
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LRiver.PROPERTY_BANKS, result);
      
      return result;
   }

   public LBankPO createBanksPO(String modifier)
   {
      LBankPO result = new LBankPO(new LBank[]{});
      
      result.setModifier(modifier);
      super.hasLink(LRiver.PROPERTY_BANKS, result);
      
      return result;
   }

   public LRiverPO createBanksLink(LBankPO tgt)
   {
      return hasLinkConstraint(tgt, LRiver.PROPERTY_BANKS);
   }

   public LRiverPO createBanksLink(LBankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LRiver.PROPERTY_BANKS, modifier);
   }

   public LBankSet getBanks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LRiver) this.getCurrentMatch()).getBanks();
      }
      return null;
   }

}
