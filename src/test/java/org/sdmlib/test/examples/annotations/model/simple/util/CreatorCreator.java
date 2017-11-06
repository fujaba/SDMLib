package org.sdmlib.test.examples.annotations.model.simple.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new CubeCreator());
      jsonIdMap.with(new CubePOCreator());
      jsonIdMap.with(new DoorCreator());
      jsonIdMap.with(new DoorPOCreator());
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      jsonIdMap.with(new WindowCreator());
      jsonIdMap.with(new WindowPOCreator());
      return jsonIdMap;
   }
}
