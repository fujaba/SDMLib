package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new UBankCreator());
      jsonIdMap.with(new UBankPOCreator());
      jsonIdMap.with(new UBoatCreator());
      jsonIdMap.with(new UBoatPOCreator());
      jsonIdMap.with(new UCargoCreator());
      jsonIdMap.with(new UCargoPOCreator());
      jsonIdMap.with(new URiverCreator());
      jsonIdMap.with(new URiverPOCreator());
      return jsonIdMap;
   }
}
