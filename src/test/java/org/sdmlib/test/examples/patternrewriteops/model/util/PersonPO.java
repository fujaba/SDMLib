package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;


public class PersonPO extends PatternObject<PersonPO, Person>
{

      /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TrainPO createTrainPO()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TRAIN, result);
      
      return result;
   }

   public TrainPO createTrainPO(String modifier)
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_TRAIN, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public PersonPO createTrainLink(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TRAIN);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public PersonPO createTrainLink(TrainPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TRAIN, modifier);
   }

   public Train getTrain()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTrain();
      }
      return null;
   }

   public StationPO createStationPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStationPO(String modifier)
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_STATION, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public PersonPO createStationLink(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_STATION);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public PersonPO createStationLink(StationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_STATION, modifier);
   }

   public Station getStation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getStation();
      }
      return null;
   }



}
