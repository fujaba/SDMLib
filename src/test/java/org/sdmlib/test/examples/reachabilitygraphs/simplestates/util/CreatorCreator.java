package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new NodeCreator());
      jsonIdMap.with(new NodePOCreator());
      jsonIdMap.with(new SimpleStateCreator());
      jsonIdMap.with(new SimpleStatePOCreator());
      return jsonIdMap;
   }
}
