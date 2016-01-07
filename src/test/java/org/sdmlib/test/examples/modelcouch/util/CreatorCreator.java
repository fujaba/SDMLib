package org.sdmlib.test.examples.modelcouch.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new DocumentDataCreator());
      jsonIdMap.withCreator(new DocumentDataPOCreator());
      jsonIdMap.withCreator(new TaskCreator());
      jsonIdMap.withCreator(new TaskPOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      return jsonIdMap;
   }
}
