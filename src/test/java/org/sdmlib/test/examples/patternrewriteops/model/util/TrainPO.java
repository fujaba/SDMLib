package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.Station;

   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
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
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public TrainPO(){
      newInstance(null);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public TrainPO(Train... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
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

   public PersonSet getPassengers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Train) this.getCurrentMatch()).getPassengers();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public StationPO createStationPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Train.PROPERTY_STATION, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
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

   public Station getStation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Train) this.getCurrentMatch()).getStation();
      }
      return null;
   }
}
