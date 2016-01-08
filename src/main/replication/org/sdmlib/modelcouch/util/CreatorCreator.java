package org.sdmlib.modelcouch.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new ModelCouchCreator());
      jsonIdMap.withCreator(new ModelCouchPOCreator());
      jsonIdMap.withCreator(new ModelDBListenerCreator());
      jsonIdMap.withCreator(new ModelDBListenerPOCreator());
      return jsonIdMap;
   }
}
