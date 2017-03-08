package org.sdmlib.test.examples.roombook.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new BuildingCreator());
      jsonIdMap.with(new BuildingPOCreator());
      jsonIdMap.with(new FloorCreator());
      jsonIdMap.with(new FloorPOCreator());
      return jsonIdMap;
   }
}
