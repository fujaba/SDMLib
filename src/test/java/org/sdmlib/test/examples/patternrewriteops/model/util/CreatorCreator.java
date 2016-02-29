package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new TrainCreator());
      jsonIdMap.with(new TrainPOCreator());
      jsonIdMap.with(new StationCreator());
      jsonIdMap.with(new StationPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new SignalFlagCreator());
      jsonIdMap.with(new SignalFlagPOCreator());
      return jsonIdMap;
   }
}

