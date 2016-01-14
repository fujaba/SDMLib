package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new AlexCreator());
      jsonIdMap.with(new AlexPOCreator());
      jsonIdMap.with(new MacCreator());
      jsonIdMap.with(new MacPOCreator());
      return jsonIdMap;
   }
}
