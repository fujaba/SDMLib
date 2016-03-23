package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new AlexCreator());
      jsonIdMap.with(new AlexPOCreator());
      jsonIdMap.with(new MacCreator());
      jsonIdMap.with(new MacPOCreator());
      return jsonIdMap;
   }
}
