package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

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


   public TrainPO(){
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public TrainPO(Train... hostGraphObject) {
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
      PersonPO result = new PersonPO(new Person[]{});
      
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

