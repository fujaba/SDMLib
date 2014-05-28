package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.examples.patternrewriteops.model.util.SignalFlagSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.examples.patternrewriteops.model.util.StationSet;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public SignalFlagPO(SignalFlag... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
          return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
           this.withCandidates(hostGraphObject);
      } else {
           this.withCandidates(hostGraphObject[0]);
      }
      pattern.findMatch();
  }
   public StationPO hasStation()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SignalFlag.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStation()
   {
      return this.startCreate().hasStation().endCreate();
   }

   public SignalFlagPO hasStation(StationPO tgt)
   {
      return hasLinkConstraint(tgt, SignalFlag.PROPERTY_STATION);
   }

   public SignalFlagPO createStation(StationPO tgt)
   {
      return this.startCreate().hasStation(tgt).endCreate();
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

