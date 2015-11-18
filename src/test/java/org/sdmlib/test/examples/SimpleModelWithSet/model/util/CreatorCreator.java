package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      jsonIdMap.withCreator(new ChildCreator());
      jsonIdMap.withCreator(new ChildPOCreator());
      jsonIdMap.withCreator(new SimpleKeyValueListCreator());
      jsonIdMap.withCreator(new SimpleKeyValueListPOCreator());
      return jsonIdMap;
   }
}
