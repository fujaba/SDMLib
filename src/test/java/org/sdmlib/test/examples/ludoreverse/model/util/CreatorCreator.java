package org.sdmlib.test.examples.ludoreverse.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator {

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.LudoCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.LudoPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.PointCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.PointPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.PlayerCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludoreverse.model.util.PlayerPOCreator());

      return jsonIdMap;
   }
}
