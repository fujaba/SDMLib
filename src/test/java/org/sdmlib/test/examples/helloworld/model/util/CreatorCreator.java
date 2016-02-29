package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      jsonIdMap.with(new GraphCreator());
      jsonIdMap.with(new GraphPOCreator());
      jsonIdMap.with(new EdgeCreator());
      jsonIdMap.with(new EdgePOCreator());

      jsonIdMap.with(new GraphComponentCreator());
      jsonIdMap.with(new GraphComponentPOCreator());
      return jsonIdMap;
   }
}
