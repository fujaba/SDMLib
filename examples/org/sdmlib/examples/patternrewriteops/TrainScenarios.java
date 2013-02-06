package org.sdmlib.examples.patternrewriteops;

import junit.framework.Assert;

import org.junit.Test;
import org.sdmlib.examples.patternrewriteops.creators.ModelPattern;
import org.sdmlib.examples.patternrewriteops.creators.PersonPO;
import org.sdmlib.examples.patternrewriteops.creators.SignalFlagPO;
import org.sdmlib.examples.patternrewriteops.creators.StationPO;
import org.sdmlib.examples.patternrewriteops.creators.TrainPO;
import org.sdmlib.scenarios.Scenario;

public class TrainScenarios
{
	@Test 
	public void trainCollectPassengers()
	{
      Scenario scenario = new Scenario("examples");
      
      Train train = new Train();
      
      Station stat1 = train.createStation();
      
      Station stat3 = stat1.createNext().createNext();
      
      stat3.createNext();
      
      stat1.createPeople();
      stat1.createPeople();
      stat1.createPeople();
      
      stat1.createFlag();

//      stat3.createPeople();
//      stat3.createPeople();
//
//      stat3.createFlag();
      
      scenario.addObjectDiag(train);
      
      // collect people from first station
      ModelPattern pattern = new ModelPattern();
      
      TrainPO trainPO = pattern.hasElementTrainPO(train);
      
      StationPO stationPO = trainPO.hasStation();
      
      SignalFlagPO flagPO = stationPO.hasFlag();
      
      PersonPO personPO = stationPO.hasPeople();
      
      // flagPO.destroy();
      
      personPO.startCreate().hasTrain(trainPO);
      
      pattern.startDestroy();
      
      // stationPO.hasFlag();
      
      // personPO.hasStation(stationPO);
      
      stationPO.hasPeople(personPO);
      
      personPO.allMatches();
      
      // Assert.assertEquals("station should be vacant",  0, stat1.getPeople().size());
      
      scenario.add(pattern.dumpDiagram("addPassengersPattern"));
      
      
      scenario.dumpHTML();
	}
}
