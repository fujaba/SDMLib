package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
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
