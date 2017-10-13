package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
   public TrainPO(){
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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
     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

   public StationPO filterStation()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Train.PROPERTY_STATION, result);
      
      return result;
   }

   public TrainPO filterStation(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_STATION);
   }

   public PersonPO filterPassengers()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Train.PROPERTY_PASSENGERS, result);
      
      return result;
   }

   public TrainPO filterPassengers(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_PASSENGERS);
   }


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public TrainPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createPassengersPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Train.PROPERTY_PASSENGERS, result);
      
      return result;
   }

   public PersonPO createPassengersPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Train.PROPERTY_PASSENGERS, result);
      
      return result;
   }

   public TrainPO createPassengersLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_PASSENGERS);
   }

   public TrainPO createPassengersLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_PASSENGERS, modifier);
   }

   public StationPO createStationPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Train.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStationPO(String modifier)
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(modifier);
      super.hasLink(Train.PROPERTY_STATION, result);
      
      return result;
   }

   public TrainPO createStationLink(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_STATION);
   }

   public TrainPO createStationLink(StationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Train.PROPERTY_STATION, modifier);
   }

}

