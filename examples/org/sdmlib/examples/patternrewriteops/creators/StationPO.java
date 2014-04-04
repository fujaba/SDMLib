package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.SignalFlag;
import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.creators.StationSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;
import org.sdmlib.examples.patternrewriteops.creators.SignalFlagPO;

public class StationPO extends PatternObject<StationPO, Station>
{
   public StationSet allMatches()
   {
      this.setDoAllMatches(true);

      StationSet matches = new StationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Station) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public TrainPO hasTrains()
   {
      TrainPO result = new TrainPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Station.PROPERTY_TRAINS, result);

      return result;
   }

   public StationPO hasTrains(TrainPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Station.PROPERTY_TRAINS).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public TrainSet getTrains()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getTrains();
      }
      return null;
   }

   public StationPO hasNext()
   {
      StationPO result = new StationPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Station.PROPERTY_NEXT, result);

      return result;
   }

   public StationPO hasNext(StationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Station.PROPERTY_NEXT).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Station getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public StationPO hasPrev()
   {
      StationPO result = new StationPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Station.PROPERTY_PREV, result);

      return result;
   }

   public StationPO hasPrev(StationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Station.PROPERTY_PREV).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Station getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public PersonPO hasPeople()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Station.PROPERTY_PEOPLE, result);

      return result;
   }

   public StationPO hasPeople(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Station.PROPERTY_PEOPLE).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public PersonSet getPeople()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getPeople();
      }
      return null;
   }

   public SignalFlagPO hasFlag()
   {
      SignalFlagPO result = new SignalFlagPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Station.PROPERTY_FLAG, result);

      return result;
   }

   public StationPO hasFlag(SignalFlagPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Station.PROPERTY_FLAG).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public SignalFlag getFlag()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getFlag();
      }
      return null;
   }

   public TrainPO createTrains()
   {
      return this.startCreate().hasTrains().endCreate();
   }

   public StationPO createTrains(TrainPO tgt)
   {
      return this.startCreate().hasTrains(tgt).endCreate();
   }

   public StationPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public StationPO createNext(StationPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public StationPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public StationPO createPrev(StationPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public PersonPO createPeople()
   {
      return this.startCreate().hasPeople().endCreate();
   }

   public StationPO createPeople(PersonPO tgt)
   {
      return this.startCreate().hasPeople(tgt).endCreate();
   }

   public SignalFlagPO createFlag()
   {
      return this.startCreate().hasFlag().endCreate();
   }

   public StationPO createFlag(SignalFlagPO tgt)
   {
      return this.startCreate().hasFlag(tgt).endCreate();
   }

}
