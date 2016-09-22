package org.sdmlib.simple.model.issue30.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new ZoombieOwnerCreator());
      jsonIdMap.with(new ZoombieOwnerPOCreator());
      jsonIdMap.with(new ACreator());
      jsonIdMap.with(new APOCreator());
      jsonIdMap.with(new GroundCreator());
      jsonIdMap.with(new GroundPOCreator());
      return jsonIdMap;
   }
}
