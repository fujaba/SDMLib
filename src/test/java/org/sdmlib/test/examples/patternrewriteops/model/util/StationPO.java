package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;

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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
*/
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

   public TrainPO filterTrains()
   {
      TrainPO result = new TrainPO(new Train[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_TRAINS, result);
      
      return result;
   }

   public StationPO filterTrains(TrainPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_TRAINS);
   }

   public StationPO filterPrev()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PREV, result);
      
      return result;
   }

   public StationPO filterPrev(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PREV);
   }

   public PersonPO filterPeople()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_PEOPLE, result);
      
      return result;
   }

   public StationPO filterPeople(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_PEOPLE);
   }

   public SignalFlagPO filterFlag()
   {
      SignalFlagPO result = new SignalFlagPO(new SignalFlag[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_FLAG, result);
      
      return result;
   }

   public StationPO filterFlag(SignalFlagPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_FLAG);
   }

   public StationPO filterNext()
   {
      StationPO result = new StationPO(new Station[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Station.PROPERTY_NEXT, result);
      
      return result;
   }

   public StationPO filterNext(StationPO tgt)
   {
      return hasLinkConstraint(tgt, Station.PROPERTY_NEXT);
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
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

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
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

}

