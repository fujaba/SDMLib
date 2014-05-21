package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.model.Person;
import org.sdmlib.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.util.TrainPO;
import org.sdmlib.examples.patternrewriteops.model.Train;

public class PersonPO extends PatternObject<PersonPO, Person>
{

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
   
   public StationPO hasStation()
   {
      StationPO result = new StationPO();
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
      TrainPO result = new TrainPO();
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

}

