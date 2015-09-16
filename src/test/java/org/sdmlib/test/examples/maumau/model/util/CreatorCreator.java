package org.sdmlib.test.examples.maumau.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new MauMauCreator());
      jsonIdMap.withCreator(new MauMauPOCreator());
      jsonIdMap.withCreator(new CardCreator());
      jsonIdMap.withCreator(new CardPOCreator());
      jsonIdMap.withCreator(new HolderCreator());
      jsonIdMap.withCreator(new HolderPOCreator());
      jsonIdMap.withCreator(new PlayerCreator());
      jsonIdMap.withCreator(new PlayerPOCreator());
      jsonIdMap.withCreator(new DutyCreator());
      jsonIdMap.withCreator(new DutyPOCreator());
      jsonIdMap.withCreator(new OpenStackCreator());
      jsonIdMap.withCreator(new OpenStackPOCreator());
      jsonIdMap.withCreator(new DrawingStackCreator());
      jsonIdMap.withCreator(new DrawingStackPOCreator());
      jsonIdMap.withCreator(new LaneCreator());
      jsonIdMap.withCreator(new LanePOCreator());
      return jsonIdMap;
   }
}
