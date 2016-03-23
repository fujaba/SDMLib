package org.sdmlib.simple.model.attribute_h.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new SimpleKeyValueListCreator());
      jsonIdMap.with(new SimpleKeyValueListPOCreator());
      return jsonIdMap;
   }
}
