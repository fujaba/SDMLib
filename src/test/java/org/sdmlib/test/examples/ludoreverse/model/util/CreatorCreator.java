package org.sdmlib.test.examples.ludoreverse.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator {

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      jsonIdMap.with(new LudoCreator());
      jsonIdMap.with(new LudoPOCreator());
      jsonIdMap.with(new PointCreator());
      jsonIdMap.with(new PointPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      return jsonIdMap;
   }
}
