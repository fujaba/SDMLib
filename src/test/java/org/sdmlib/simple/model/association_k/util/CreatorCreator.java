package org.sdmlib.simple.model.association_k.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new TaskCreator());
      jsonIdMap.with(new TaskPOCreator());
      return jsonIdMap;
   }
}
