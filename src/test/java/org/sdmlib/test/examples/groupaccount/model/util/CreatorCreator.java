package org.sdmlib.test.examples.groupaccount.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new ItemCreator());
      jsonIdMap.with(new ItemPOCreator());
      jsonIdMap.with(new PartyCreator());
      jsonIdMap.with(new PartyPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      return jsonIdMap;
   }
}
