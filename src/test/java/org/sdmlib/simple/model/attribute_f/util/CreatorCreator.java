package org.sdmlib.simple.model.attribute_f.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new SimpleSetCreator());
      jsonIdMap.with(new SimpleSetPOCreator());
      return jsonIdMap;
   }
}
