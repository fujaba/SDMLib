package org.sdmlib.examples.features.model.all.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.HouseCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.HousePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.DoorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.DoorPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.WindowCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.features.model.all.util.WindowPOCreator());

      return jsonIdMap;
   }
}
