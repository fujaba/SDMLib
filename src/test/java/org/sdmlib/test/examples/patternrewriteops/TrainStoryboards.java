package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainCreator;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;

public class TrainStoryboards
{
   protected Person p1;
   protected Person p2;
   protected Person p3;

   @Test 
   public void trainCollectPassengers()
   {
      Storyboard storyboard = new Storyboard("examples");

      Train train = new Train();

      Station stat1 = train.createStation();

      Station stat3 = stat1.createNext().createNext();

      stat3.createNext();

      p1 = stat1.createPeople();
      p2 = stat1.createPeople();
      p3 = stat1.createPeople();

      stat1.createFlag();

      stat3.createPeople();
      stat3.createPeople();

      stat3.createFlag();

      storyboard.withMap(TrainCreator.createIdMap("Train"));
      
      storyboard.addObjectDiagram(train);
      
      int i = 1;

      do 
      {
         // collect people from first station
//         ModelPattern pattern = new ModelPattern();
         TrainPO trainPO = new TrainPO(train);
//         TrainPO trainPO = pattern.hasElementTrainPO(train);

         StationPO stationPO = trainPO.hasStation();

         SignalFlagPO flagPO = stationPO.hasFlag()
               .destroy();

         stationPO.startSubPattern();
         
         storyboard.dumpDiagram(trainPO, ("addPassengersPattern"+i+"-0"));

         StationPO stationDestroyPO = stationPO.startDestroy();
         storyboard.dumpDiagram(trainPO, ("addPassengersPattern"+i+"-1"));
         PersonPO personPO = stationDestroyPO.hasPeople();
         
         storyboard.dumpDiagram(trainPO, ("addPassengersPattern"+i+"-2"));

         personPO.startCreate().hasTrain(trainPO);
         storyboard.dumpDiagram(trainPO, ("addPassengersPattern"+i+"-3"));
         // personPO.startDestroy().hasStation(stationPO);

         personPO.allMatches();

         storyboard.dumpDiagram(trainPO, ("addPassengersPattern"+i+"-4"));
//         storyboard.add(pattern.dumpDiagram("addPassengersPattern" + i));
         
         storyboard.add("step " + i);
         storyboard.addPattern(trainPO, true);
         i++;
         
         train.setStation(train.getStation().getNext());
      }
      while (train.getStation() != null);
      
      storyboard.dumpHTML();

      Assert.assertEquals("station should be vacant ",  0, stat1.getPeople().size());
   }
}
