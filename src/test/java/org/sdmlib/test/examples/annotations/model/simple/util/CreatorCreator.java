package org.sdmlib.test.examples.annotations.model.simple.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new CubeCreator());
      jsonIdMap.with(new CubePOCreator());
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      jsonIdMap.with(new DoorCreator());
      jsonIdMap.with(new DoorPOCreator());
      jsonIdMap.with(new WindowCreator());
      jsonIdMap.with(new WindowPOCreator());
      return jsonIdMap;
   }
}
