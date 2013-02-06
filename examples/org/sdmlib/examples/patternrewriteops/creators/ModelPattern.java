package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;
import org.sdmlib.examples.patternrewriteops.Train;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;
import org.sdmlib.examples.patternrewriteops.Person;
import org.sdmlib.examples.patternrewriteops.creators.SignalFlagPO;
import org.sdmlib.examples.patternrewriteops.SignalFlag;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public TrainPO hasElementTrainPO()
   {
      TrainPO value = new TrainPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TrainPO hasElementTrainPO(Train hostGraphObject)
   {
      TrainPO value = new TrainPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StationPO hasElementStationPO()
   {
      StationPO value = new StationPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StationPO hasElementStationPO(Station hostGraphObject)
   {
      StationPO value = new StationPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PersonPO hasElementPersonPO()
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PersonPO hasElementPersonPO(Person hostGraphObject)
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SignalFlagPO hasElementSignalFlagPO()
   {
      SignalFlagPO value = new SignalFlagPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SignalFlagPO hasElementSignalFlagPO(SignalFlag hostGraphObject)
   {
      SignalFlagPO value = new SignalFlagPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



