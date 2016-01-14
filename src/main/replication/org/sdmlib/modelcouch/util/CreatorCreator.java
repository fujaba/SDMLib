package org.sdmlib.modelcouch.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new ModelCouchCreator());
      jsonIdMap.with(new ModelCouchPOCreator());
      jsonIdMap.with(new ModelDBListenerCreator());
      jsonIdMap.with(new ModelDBListenerPOCreator());
      return jsonIdMap;
   }
}
