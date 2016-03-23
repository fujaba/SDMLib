package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
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
