package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationSet;

public class SignalFlagPO extends PatternObject<SignalFlagPO, SignalFlag>
{

    public SignalFlagSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SignalFlagSet matches = new SignalFlagSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SignalFlag) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SignalFlagPO(){
      newInstance(null);
   }

   public SignalFlagPO(SignalFlag... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public SignalFlagPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StationPO createStationPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SignalFlag.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStationPO(String modifier)
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(modifier);
      super.hasLink(SignalFlag.PROPERTY_STATION, result);
      
      return result;
   }

   public SignalFlagPO createStationLink(StationPO tgt)
   {
      return hasLinkConstraint(tgt, SignalFlag.PROPERTY_STATION);
   }

   public SignalFlagPO createStationLink(StationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SignalFlag.PROPERTY_STATION, modifier);
   }

   public StationSet getStation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SignalFlag) this.getCurrentMatch()).getStation();
      }
      return null;
   }

}
