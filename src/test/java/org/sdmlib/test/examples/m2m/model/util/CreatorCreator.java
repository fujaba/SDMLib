package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new GraphComponentCreator());
      jsonIdMap.with(new GraphComponentPOCreator());
      jsonIdMap.with(new GraphCreator());
      jsonIdMap.with(new GraphPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new RelationCreator());
      jsonIdMap.with(new RelationPOCreator());
      return jsonIdMap;
   }
}
