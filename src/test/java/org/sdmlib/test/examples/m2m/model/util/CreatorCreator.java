package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.GraphComponentCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.GraphComponentPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.GraphCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.GraphPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.RelationCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.m2m.model.util.RelationPOCreator());

      return jsonIdMap;
   }
}
