package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new CargoCreator());
      jsonIdMap.with(new CargoPOCreator());
      jsonIdMap.with(new LBankCreator());
      jsonIdMap.with(new LBankPOCreator());
      jsonIdMap.with(new LBoatCreator());
      jsonIdMap.with(new LBoatPOCreator());
      jsonIdMap.with(new LRiverCreator());
      jsonIdMap.with(new LRiverPOCreator());
      return jsonIdMap;
   }
}
