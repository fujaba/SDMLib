package org.sdmlib.test.examples.mancala.referencemodel.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      
      jsonIdMap.with(new ColorCreator());
      jsonIdMap.with(new ColorPOCreator());
      return jsonIdMap;
   }
}
