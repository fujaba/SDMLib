package org.sdmlib.test.model.refactoring.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);


      jsonIdMap.withCreator(new PlayerCreator());
      jsonIdMap.withCreator(new PlayerPOCreator());
      return jsonIdMap;
   }


}
