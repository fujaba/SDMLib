package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
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















