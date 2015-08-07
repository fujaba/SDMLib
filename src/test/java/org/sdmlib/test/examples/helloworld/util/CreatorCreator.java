package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.GreetingCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.GreetingPOCreator());

      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.GreetingMessageCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.GreetingMessagePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.helloworld.util.PersonPOCreator());
      jsonIdMap.withCreator(new GreetingCreator());
      jsonIdMap.withCreator(new GreetingPOCreator());
      jsonIdMap.withCreator(new GreetingCreator());
      jsonIdMap.withCreator(new GreetingPOCreator());
      jsonIdMap.withCreator(new GreetingMessageCreator());
      jsonIdMap.withCreator(new GreetingMessagePOCreator());
      jsonIdMap.withCreator(new PersonCreator());
      jsonIdMap.withCreator(new PersonPOCreator());
      return jsonIdMap;
   }
}















