package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.examples.patternrewriteops.Train;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.creators.TrainSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;

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

   public TrainPO hasStation(StationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Train.PROPERTY_STATION).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
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

   public TrainPO hasPassengers(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Train.PROPERTY_PASSENGERS).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public PersonSet getPassengers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Train) this.getCurrentMatch()).getPassengers();
      }
      return null;
   }

   public StationPO createStation()
   {
      return this.startCreate().hasStation().endCreate();
   }

   public TrainPO createStation(StationPO tgt)
   {
      return this.startCreate().hasStation(tgt).endCreate();
   }

   public PersonPO createPassengers()
   {
      return this.startCreate().hasPassengers().endCreate();
   }

   public TrainPO createPassengers(PersonPO tgt)
   {
      return this.startCreate().hasPassengers(tgt).endCreate();
   }

}
