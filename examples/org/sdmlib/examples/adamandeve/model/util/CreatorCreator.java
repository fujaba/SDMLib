package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.EveCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.EvePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.AdamCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.AdamPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.TaskFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.TaskFlowPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.PeerProxyCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.PeerProxyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.UpdateAdamFlowCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.adamandeve.model.util.UpdateAdamFlowPOCreator());

      return jsonIdMap;
   }
}
