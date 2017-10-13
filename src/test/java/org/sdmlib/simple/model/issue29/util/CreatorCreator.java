package org.sdmlib.simple.model.issue29.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new BCreator());
      jsonIdMap.with(new BPOCreator());
      jsonIdMap.with(new CCreator());
      jsonIdMap.with(new CPOCreator());
      return jsonIdMap;
   }
}
