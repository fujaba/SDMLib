package org.sdmlib.examples.mancala.referencemodel.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.referencemodel.util.ColorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.referencemodel.util.ColorPOCreator());

      return jsonIdMap;
   }
}
