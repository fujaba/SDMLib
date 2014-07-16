package org.sdmlib.serialization.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.serialization.util.JsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.serialization.util.JsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.serialization.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.serialization.util.SDMLibJsonIdMapPOCreator());
      
      return jsonIdMap;
   }
}

