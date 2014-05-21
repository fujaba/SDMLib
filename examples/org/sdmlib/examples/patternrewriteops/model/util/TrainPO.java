package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.model.Train;
import org.sdmlib.examples.patternrewriteops.model.util.TrainSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.patternrewriteops.model.util.TrainPO;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.examples.patternrewriteops.model.Person;
import org.sdmlib.examples.patternrewriteops.model.util.PersonSet;

public class TrainPO extends PatternObject<TrainPO, Train>
{

    public TrainSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TrainSet matches = new TrainSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Train) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StationPO hasStation()
   {
      StationPO result = new StationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Train.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStation()
   {
      return this.startCreate().hasStation().endCreate();
   }

   public TrainPO hasStation(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_STATION);
   }

   public TrainPO createStation(StationPO tgt)
   {
      return this.startCreate().hasStation(tgt).endCreate();
   }

   public Station getStation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Train) this.getCurrentMatch()).getStation();
      }
      return null;
   }

   public PersonPO hasPassengers()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Train.PROPERTY_PASSENGERS, result);
      
      return result;
   }

   public PersonPO createPassengers()
   {
      return this.startCreate().hasPassengers().endCreate();
   }

   public TrainPO hasPassengers(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_PASSENGERS);
   }

   public TrainPO createPassengers(PersonPO tgt)
   {
      return this.startCreate().hasPassengers(tgt).endCreate();
   }

   public PersonSet getPassengers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Train) this.getCurrentMatch()).getPassengers();
      }
      return null;
   }

}

