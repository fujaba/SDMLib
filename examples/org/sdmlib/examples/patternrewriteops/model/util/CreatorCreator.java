package org.sdmlib.examples.patternrewriteops.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.TrainCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.TrainPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.StationCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.StationPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.SignalFlagCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.patternrewriteops.model.util.SignalFlagPOCreator());

      return jsonIdMap;
   }
}

