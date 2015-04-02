package org.sdmlib.examples.annotations.model.simple.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.CubeCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.CubePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.HouseCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.HousePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.DoorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.DoorPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.WindowCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.annotations.model.simple.util.WindowPOCreator());

      return jsonIdMap;
   }
}
