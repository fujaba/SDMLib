package org.sdmlib.modelcouch.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new ModelCouchCreator());
      jsonIdMap.with(new ModelCouchPOCreator());
      jsonIdMap.with(new ModelDBListenerCreator());
      jsonIdMap.with(new ModelDBListenerPOCreator());
      return jsonIdMap;
   }
}
