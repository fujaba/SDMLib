package org.sdmlib.examples.helloworld.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.NodeCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.NodePOCreator());

      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.GraphCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.GraphPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.EdgeCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.EdgePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.GraphComponentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.model.util.GraphComponentPOCreator());
      return jsonIdMap;
   }
}
















































































