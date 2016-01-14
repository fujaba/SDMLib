package org.sdmlib.test.examples.maumau.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new MauMauCreator());
      jsonIdMap.with(new MauMauPOCreator());
      jsonIdMap.with(new CardCreator());
      jsonIdMap.with(new CardPOCreator());
      jsonIdMap.with(new HolderCreator());
      jsonIdMap.with(new HolderPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new DutyCreator());
      jsonIdMap.with(new DutyPOCreator());
      jsonIdMap.with(new OpenStackCreator());
      jsonIdMap.with(new OpenStackPOCreator());
      jsonIdMap.with(new DrawingStackCreator());
      jsonIdMap.with(new DrawingStackPOCreator());
      jsonIdMap.with(new LaneCreator());
      jsonIdMap.with(new LanePOCreator());
      return jsonIdMap;
   }
}
