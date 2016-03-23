package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new SimpleStateCreator());
      jsonIdMap.with(new SimpleStatePOCreator());
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      return jsonIdMap;
   }
}
