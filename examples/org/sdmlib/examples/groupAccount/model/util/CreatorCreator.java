package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.GroupAccountCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.GroupAccountPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.ItemCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.groupAccount.model.util.ItemPOCreator());

      return jsonIdMap;
   }
}

