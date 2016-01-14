package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new GreetingCreator());
      jsonIdMap.with(new GreetingPOCreator());
      jsonIdMap.with(new GreetingCreator());
      jsonIdMap.with(new GreetingPOCreator());
      jsonIdMap.with(new GreetingMessageCreator());
      jsonIdMap.with(new GreetingMessagePOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      return jsonIdMap;
   }
}















