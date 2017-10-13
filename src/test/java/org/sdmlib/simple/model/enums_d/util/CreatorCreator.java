package org.sdmlib.simple.model.enums_d.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new IntegerCreator());
      jsonIdMap.with(new IntegerPOCreator());
      return jsonIdMap;
   }
}
