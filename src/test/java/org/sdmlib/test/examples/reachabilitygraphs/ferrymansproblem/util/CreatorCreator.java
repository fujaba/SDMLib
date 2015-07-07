package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPOCreator());

      return jsonIdMap;
   }
}
