package org.sdmlib.test.examples.features.model.all.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.HouseCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.HousePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.DoorCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.DoorPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.WindowCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.features.model.all.util.WindowPOCreator());

      return jsonIdMap;
   }
}
