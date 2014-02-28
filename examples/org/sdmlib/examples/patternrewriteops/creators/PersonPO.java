package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.Person;
import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.examples.patternrewriteops.Train;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.patternrewriteops.creators.PersonSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;

public class PersonPO extends PatternObject<PersonPO, Person>
{
   public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern()
         .findMatch();
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

   public PersonPO hasStation(StationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_STATION)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

   public PersonPO hasTrain(TrainPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_TRAIN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Train getTrain()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTrain();
      }
      return null;
   }

   public StationPO createStation()
   {
      return this.startCreate().hasStation().endCreate();
   }

   public PersonPO createStation(StationPO tgt)
   {
      return this.startCreate().hasStation(tgt).endCreate();
   }

   public TrainPO createTrain()
   {
      return this.startCreate().hasTrain().endCreate();
   }

   public PersonPO createTrain(TrainPO tgt)
   {
      return this.startCreate().hasTrain(tgt).endCreate();
   }

}


