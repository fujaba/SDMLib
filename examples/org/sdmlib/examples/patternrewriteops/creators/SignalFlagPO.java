package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.SignalFlag;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
   
   public StationPO hasStation()
   {
      StationPO result = new StationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(SignalFlag.PROPERTY_STATION, result);
      
      return result;
   }

   public SignalFlagPO hasStation(StationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(SignalFlag.PROPERTY_STATION)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

