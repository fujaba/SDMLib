package org.sdmlib.simple.model.issue31.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new ACreator());
      jsonIdMap.with(new APOCreator());
      jsonIdMap.with(new BCreator());
      jsonIdMap.with(new BPOCreator());
      jsonIdMap.with(new CCreator());
      jsonIdMap.with(new CPOCreator());
      return jsonIdMap;
   }
}
