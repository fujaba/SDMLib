package org.sdmlib.simple.model.abstract_B.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new FlowerCreator());
      jsonIdMap.with(new FlowerPOCreator());
      jsonIdMap.with(new HumanCreator());
      jsonIdMap.with(new HumanPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      return jsonIdMap;
   }
}
