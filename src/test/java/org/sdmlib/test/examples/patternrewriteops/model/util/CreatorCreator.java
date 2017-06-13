package org.sdmlib.test.examples.patternrewriteops.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new SignalFlagCreator());
      jsonIdMap.with(new SignalFlagPOCreator());
      jsonIdMap.with(new StationCreator());
      jsonIdMap.with(new StationPOCreator());
      jsonIdMap.with(new TrainCreator());
      jsonIdMap.with(new TrainPOCreator());
      return jsonIdMap;
   }
}
