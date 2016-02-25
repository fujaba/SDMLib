package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public PersonPO(Person... hostGraphObject) {
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
      super.hasLink(Person.PROPERTY_STATION, result);
      
      return result;
   }

   public StationPO createStation()
   {
      return this.startCreate().hasStation().endCreate();
   }

   public PersonPO hasStation(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_STATION);
   }

   public PersonPO createStation(StationPO tgt)
   {
      return this.startCreate().hasStation(tgt).endCreate();
   }

   public Station getStation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getStation();
      }
      return null;
   }

   public TrainPO hasTrain()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TRAIN, result);
      
      return result;
   }

   public TrainPO createTrain()
   {
      return this.startCreate().hasTrain().endCreate();
   }

   public PersonPO hasTrain(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TRAIN);
   }

   public PersonPO createTrain(TrainPO tgt)
   {
      return this.startCreate().hasTrain(tgt).endCreate();
   }

   public Train getTrain()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTrain();
      }
      return null;
   }

   public StationPO filterStation()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_STATION, result);
      
      return result;
   }

   public PersonPO filterStation(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_STATION);
   }

   public TrainPO filterTrain()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TRAIN, result);
      
      return result;
   }

   public PersonPO filterTrain(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TRAIN);
   }

}

