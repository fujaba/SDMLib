package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new SimpleStateCreator());
      jsonIdMap.with(new SimpleStatePOCreator());
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      return jsonIdMap;
   }
}
