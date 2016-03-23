package org.sdmlib.simple.model.enums_d.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new IntegerCreator());
      jsonIdMap.with(new IntegerPOCreator());
      return jsonIdMap;
   }
}
