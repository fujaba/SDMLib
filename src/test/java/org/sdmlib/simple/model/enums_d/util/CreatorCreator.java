package org.sdmlib.simple.model.enums_d.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new IntegerCreator());
      jsonIdMap.with(new IntegerPOCreator());
      return jsonIdMap;
   }
}
