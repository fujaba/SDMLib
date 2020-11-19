package de.uniks.se1.ss18.teamb.UoM.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new GameCreator());
      jsonIdMap.with(new GamePOCreator());
      return jsonIdMap;
   }
}
