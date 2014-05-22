package org.sdmlib.examples.helloworld.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.NodeCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.NodePOCreator());

      return jsonIdMap;
   }
}

