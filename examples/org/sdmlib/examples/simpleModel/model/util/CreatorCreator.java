package org.sdmlib.examples.simpleModel.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.AlexCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.AlexPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.MacCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.MacPOCreator());

      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.MacListCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.simpleModel.model.util.MacListPOCreator());
      return jsonIdMap;
   }
}
