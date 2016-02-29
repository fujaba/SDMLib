package org.sdmlib.test.examples.features.model.albertsets.util;

import de.uniks.networkparser.IdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

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
