package org.sdmlib.simple.model.attribute_a.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      return jsonIdMap;
   }
}
