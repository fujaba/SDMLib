package org.sdmlib.logger.util;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.logger.util.JsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.JsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.LoggerCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.PeerProxyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.TaskFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.TaskFlowPOCreator());
      
      return jsonIdMap;
   }
}
