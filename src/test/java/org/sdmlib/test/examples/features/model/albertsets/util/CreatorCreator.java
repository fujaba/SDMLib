package org.sdmlib.test.examples.features.model.albertsets.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      jsonIdMap.with(new DoorCreator());
      jsonIdMap.with(new DoorPOCreator());
      jsonIdMap.with(new WindowCreator());
      jsonIdMap.with(new WindowPOCreator());
      return jsonIdMap;
   }
}
