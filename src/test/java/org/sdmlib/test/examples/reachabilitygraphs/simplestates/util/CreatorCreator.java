package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      jsonIdMap.with(new SimpleStateCreator());
      jsonIdMap.with(new SimpleStatePOCreator());
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      return jsonIdMap;
   }
}
