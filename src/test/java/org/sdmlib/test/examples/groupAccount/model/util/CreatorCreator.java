package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.GroupAccountCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.GroupAccountPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.ItemCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.groupaccount.model.util.ItemPOCreator());

      return jsonIdMap;
   }
}
