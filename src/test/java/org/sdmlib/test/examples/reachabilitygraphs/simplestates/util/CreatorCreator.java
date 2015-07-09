package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePOCreator());

      return jsonIdMap;
   }
}
