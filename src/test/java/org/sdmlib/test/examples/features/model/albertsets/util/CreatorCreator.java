package org.sdmlib.test.examples.features.model.albertsets.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.HouseCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.HousePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.DoorCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.DoorPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.WindowCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.albertsets.util.WindowPOCreator());

      jsonIdMap.withCreator(new HouseCreator());
      jsonIdMap.withCreator(new HousePOCreator());
      jsonIdMap.withCreator(new DoorCreator());
      jsonIdMap.withCreator(new DoorPOCreator());
      jsonIdMap.withCreator(new WindowCreator());
      jsonIdMap.withCreator(new WindowPOCreator());
      return jsonIdMap;
   }
}
