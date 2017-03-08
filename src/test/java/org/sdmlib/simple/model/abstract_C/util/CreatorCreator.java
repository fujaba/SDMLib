package org.sdmlib.simple.model.abstract_C.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new GameCreator());
      jsonIdMap.with(new GamePOCreator());
      jsonIdMap.with(new GrassCreator());
      jsonIdMap.with(new GrassPOCreator());
      jsonIdMap.with(new GroundCreator());
      jsonIdMap.with(new GroundPOCreator());
      return jsonIdMap;
   }
}
