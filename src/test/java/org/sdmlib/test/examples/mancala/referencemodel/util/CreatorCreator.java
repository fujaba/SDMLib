package org.sdmlib.test.examples.mancala.referencemodel.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new ColorCreator());
      jsonIdMap.with(new ColorPOCreator());
      return jsonIdMap;
   }
}
