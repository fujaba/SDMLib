package org.sdmlib.examples.patternrewriteops;

import junit.framework.Assert;

import org.junit.Test;
import org.sdmlib.examples.patternrewriteops.creators.ModelPattern;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;
import org.sdmlib.examples.patternrewriteops.creators.SignalFlagPO;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;
import org.sdmlib.storyboards.Storyboard;

public class TrainStoryboards
{
   @Test
   public void trainCollectPassengers()
   {
      Storyboard storyboard = new Storyboard("examples");

      Train train = new Train();

      Station stat1 = train.createStation();

      Station stat3 = stat1.createNext().createNext();

      stat3.createNext();

      stat1.createPeople();
      stat1.createPeople();
      stat1.createPeople();

      stat1.createFlag();

      stat3.createPeople();
      stat3.createPeople();

      stat3.createFlag();

      storyboard.addObjectDiagram(train);

      int i = 1;

      do
      {
         // collect people from first station
         ModelPattern pattern = new ModelPattern();

         TrainPO trainPO = pattern.hasElementTrainPO(train);

         StationPO stationPO = trainPO.hasStation();

         SignalFlagPO flagPO = stationPO.hasFlag().destroy();

         stationPO.startSubPattern();

         PersonPO personPO = stationPO.startDestroy().hasPeople();

         personPO.startCreate().hasTrain(trainPO);

         // personPO.startDestroy().hasStation(stationPO);

         personPO.allMatches();

         storyboard.add(pattern.dumpDiagram("addPassengersPattern" + i));
         i++;

         train.setStation(train.getStation().getNext());
      }
      while (train.getStation() != null);

      storyboard.dumpHTML();

      Assert.assertEquals("station should be vacant ", 0, stat1.getPeople()
         .size());
   }
}
