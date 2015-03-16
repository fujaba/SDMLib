package de.kassel.roombook.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new de.kassel.roombook.util.BuildingCreator());
      jsonIdMap.withCreator(new de.kassel.roombook.util.BuildingPOCreator());
      jsonIdMap.withCreator(new de.kassel.roombook.util.FloorCreator());
      jsonIdMap.withCreator(new de.kassel.roombook.util.FloorPOCreator());

      return jsonIdMap;
   }
}
