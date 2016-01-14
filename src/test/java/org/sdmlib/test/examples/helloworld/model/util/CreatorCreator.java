package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      jsonIdMap.with(new GraphCreator());
      jsonIdMap.with(new GraphPOCreator());
      jsonIdMap.with(new EdgeCreator());
      jsonIdMap.with(new EdgePOCreator());

      return jsonIdMap;
   }
}
