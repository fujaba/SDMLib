package org.sdmlib.modelspace.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new ModelSpaceCreator());
      jsonIdMap.withCreator(new ModelSpacePOCreator());
      jsonIdMap.withCreator(new ModelSpaceCreator());
      jsonIdMap.withCreator(new ModelSpacePOCreator());
      jsonIdMap.withCreator(new ModelCloudCreator());
      jsonIdMap.withCreator(new ModelCloudPOCreator());
      jsonIdMap.withCreator(new ModelCloudProxyCreator());
      jsonIdMap.withCreator(new ModelCloudProxyPOCreator());
      jsonIdMap.withCreator(new ModelSpaceCreator());
      jsonIdMap.withCreator(new ModelSpacePOCreator());
      jsonIdMap.withCreator(new ModelCloudCreator());
      jsonIdMap.withCreator(new ModelCloudPOCreator());
      jsonIdMap.withCreator(new ModelCloudProxyCreator());
      jsonIdMap.withCreator(new ModelCloudProxyPOCreator());
      jsonIdMap.withCreator(new ModelSpaceProxyCreator());
      jsonIdMap.withCreator(new ModelSpaceProxyPOCreator());
      jsonIdMap.withCreator(new ModelSpaceCreator());
      jsonIdMap.withCreator(new ModelSpacePOCreator());
      jsonIdMap.withCreator(new ModelCloudCreator());
      jsonIdMap.withCreator(new ModelCloudPOCreator());
      jsonIdMap.withCreator(new ModelCloudProxyCreator());
      jsonIdMap.withCreator(new ModelCloudProxyPOCreator());
      jsonIdMap.withCreator(new ModelSpaceProxyCreator());
      jsonIdMap.withCreator(new ModelSpaceProxyPOCreator());
      return jsonIdMap;
   }
}
