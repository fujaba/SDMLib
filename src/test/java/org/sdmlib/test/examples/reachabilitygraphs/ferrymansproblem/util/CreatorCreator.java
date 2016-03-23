package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new RiverCreator());
      jsonIdMap.with(new RiverPOCreator());
      jsonIdMap.with(new BoatCreator());
      jsonIdMap.with(new BoatPOCreator());
      jsonIdMap.with(new BankCreator());
      jsonIdMap.with(new BankPOCreator());
      jsonIdMap.with(new CargoCreator());
      jsonIdMap.with(new CargoPOCreator());
      return jsonIdMap;
   }
}
