package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new ChildCreator());
      jsonIdMap.with(new ChildPOCreator());
      jsonIdMap.with(new SimpleKeyValueListCreator());
      jsonIdMap.with(new SimpleKeyValueListPOCreator());
      return jsonIdMap;
   }
}
