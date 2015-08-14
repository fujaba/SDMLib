package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.TrainCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.TrainPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.StationCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.StationPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPOCreator());

      jsonIdMap.withCreator(new TrainCreator());
      jsonIdMap.withCreator(new TrainPOCreator());
      jsonIdMap.withCreator(new StationCreator());
      jsonIdMap.withCreator(new StationPOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      jsonIdMap.withCreator(new SignalFlagCreator());
      jsonIdMap.withCreator(new SignalFlagPOCreator());
      jsonIdMap.withCreator(new TrainCreator());
      jsonIdMap.withCreator(new TrainPOCreator());
      jsonIdMap.withCreator(new StationCreator());
      jsonIdMap.withCreator(new StationPOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      jsonIdMap.withCreator(new SignalFlagCreator());
      jsonIdMap.withCreator(new SignalFlagPOCreator());
      jsonIdMap.withCreator(new TrainCreator());
      jsonIdMap.withCreator(new TrainPOCreator());
      jsonIdMap.withCreator(new StationCreator());
      jsonIdMap.withCreator(new StationPOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      jsonIdMap.withCreator(new SignalFlagCreator());
      jsonIdMap.withCreator(new SignalFlagPOCreator());
      jsonIdMap.withCreator(new TrainCreator());
      jsonIdMap.withCreator(new TrainPOCreator());
      jsonIdMap.withCreator(new StationCreator());
      jsonIdMap.withCreator(new StationPOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      jsonIdMap.withCreator(new SignalFlagCreator());
      jsonIdMap.withCreator(new SignalFlagPOCreator());
      return jsonIdMap;
   }
}

