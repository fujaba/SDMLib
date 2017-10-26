package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;

public class TrainStoryboards
{
   protected Person p1;
   protected Person p2;
   protected Person p3;

     /**
    * 
    * @see <a href='../../../../../../../../doc/trainCollectPassengers.html'>trainCollectPassengers.html</a>
*/
   @Test 
   public void trainCollectPassengers()
   {
      Storyboard storyboard = new Storyboard();

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

//      storyboard.withMap(TrainCreator.createIdMap("Train"));
      
      storyboard.addObjectDiagram(train);
      
      int i = 1;

      do 
      {
         // collect people from first station
         TrainPO trainPO = new TrainPO(train);

         StationPO stationPO = trainPO.createStationPO();

         SignalFlagPO flagPO = stationPO.createFlagPO(Pattern.DESTROY);

         stationPO.startSubPattern();
         
         PersonPO personPO = stationPO.createPeoplePO();

         personPO.createStationLink(stationPO, Pattern.DESTROY);
         
         personPO.createTrainLink(trainPO, Pattern.CREATE);

         personPO.allMatches();
         
         stationPO.endSubPattern();

         storyboard.addObjectDiagram(train);
         
         train.setStation(train.getStation().getNext());
      }
      while (train.getStation() != null);
      
      storyboard.dumpHTML();

      storyboard.assertEquals("station should be vacant ",  0, stat1.getPeople().size());
      storyboard.assertEquals("train should have all people ",  5, train.getPassengers().size());
   }
}
