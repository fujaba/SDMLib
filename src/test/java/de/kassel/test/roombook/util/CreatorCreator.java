package de.kassel.test.roombook.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new BuildingCreator());
      jsonIdMap.with(new BuildingPOCreator());
      jsonIdMap.with(new FloorCreator());
      jsonIdMap.with(new FloorPOCreator());
      return jsonIdMap;
   }
}
