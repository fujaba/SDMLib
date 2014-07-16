package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.examples.patternrewriteops.model.Person;
import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.Train;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

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


   public StationPO(){
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public StationPO(Station... hostGraphObject) {
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
   public TrainPO hasTrains()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_TRAINS, result);
      
      return result;
   }

   public TrainPO createTrains()
   {
      return this.startCreate().hasTrains().endCreate();
   }

   public StationPO hasTrains(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_TRAINS);
   }

   public StationPO createTrains(TrainPO tgt)
   {
      return this.startCreate().hasTrains(tgt).endCreate();
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
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_NEXT, result);
      
      return result;
   }

   public StationPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public StationPO hasNext(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_NEXT);
   }

   public StationPO createNext(StationPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
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
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PREV, result);
      
      return result;
   }

   public StationPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public StationPO hasPrev(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PREV);
   }

   public StationPO createPrev(StationPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
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
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PEOPLE, result);
      
      return result;
   }

   public PersonPO createPeople()
   {
      return this.startCreate().hasPeople().endCreate();
   }

   public StationPO hasPeople(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PEOPLE);
   }

   public StationPO createPeople(PersonPO tgt)
   {
      return this.startCreate().hasPeople(tgt).endCreate();
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
      SignalFlagPO result = new SignalFlagPO(new SignalFlag[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_FLAG, result);
      
      return result;
   }

   public SignalFlagPO createFlag()
   {
      return this.startCreate().hasFlag().endCreate();
   }

   public StationPO hasFlag(SignalFlagPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_FLAG);
   }

   public StationPO createFlag(SignalFlagPO tgt)
   {
      return this.startCreate().hasFlag(tgt).endCreate();
   }

   public SignalFlag getFlag()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getFlag();
      }
      return null;
   }

}

