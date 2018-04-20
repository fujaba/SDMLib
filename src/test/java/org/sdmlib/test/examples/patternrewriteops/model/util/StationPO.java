package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

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
      newInstance(null);
   }

   public StationPO(Station... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StationPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StationPO createPrevPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PREV, result);
      
      return result;
   }

   public StationPO createPrevPO(String modifier)
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(modifier);
      super.hasLink(Station.PROPERTY_PREV, result);
      
      return result;
   }

   public StationPO createPrevLink(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PREV);
   }

   public StationPO createPrevLink(StationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PREV, modifier);
   }

   public Station getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public StationPO createNextPO()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_NEXT, result);
      
      return result;
   }

   public StationPO createNextPO(String modifier)
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(modifier);
      super.hasLink(Station.PROPERTY_NEXT, result);
      
      return result;
   }

   public StationPO createNextLink(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_NEXT);
   }

   public StationPO createNextLink(StationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_NEXT, modifier);
   }

   public Station getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getNext();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public PersonPO createPeoplePO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PEOPLE, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public PersonPO createPeoplePO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Station.PROPERTY_PEOPLE, result);
      
      return result;
   }

   public StationPO createPeopleLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PEOPLE);
   }

   public StationPO createPeopleLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PEOPLE, modifier);
   }

   public PersonSet getPeople()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getPeople();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public SignalFlagPO createFlagPO()
   {
      SignalFlagPO result = new SignalFlagPO(new SignalFlag[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_FLAG, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainStoryboards#trainCollectPassengers
 */
   public SignalFlagPO createFlagPO(String modifier)
   {
      SignalFlagPO result = new SignalFlagPO(new SignalFlag[]{});
      
      result.setModifier(modifier);
      super.hasLink(Station.PROPERTY_FLAG, result);
      
      return result;
   }

   public StationPO createFlagLink(SignalFlagPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_FLAG);
   }

   public StationPO createFlagLink(SignalFlagPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_FLAG, modifier);
   }

   public SignalFlag getFlag()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getFlag();
      }
      return null;
   }

   public TrainPO createTrainsPO()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_TRAINS, result);
      
      return result;
   }

   public TrainPO createTrainsPO(String modifier)
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(modifier);
      super.hasLink(Station.PROPERTY_TRAINS, result);
      
      return result;
   }

   public StationPO createTrainsLink(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_TRAINS);
   }

   public StationPO createTrainsLink(TrainPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_TRAINS, modifier);
   }

   public TrainSet getTrains()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Station) this.getCurrentMatch()).getTrains();
      }
      return null;
   }
}
