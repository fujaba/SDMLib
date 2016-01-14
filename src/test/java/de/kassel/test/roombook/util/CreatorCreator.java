package de.kassel.test.roombook.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new BuildingCreator());
      jsonIdMap.with(new BuildingPOCreator());
      jsonIdMap.with(new FloorCreator());
      jsonIdMap.with(new FloorPOCreator());
      return jsonIdMap;
   }
}
