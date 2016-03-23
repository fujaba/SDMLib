package org.sdmlib.serialization.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new org.sdmlib.serialization.util.JsonIdMapCreator());
      jsonIdMap.with(new org.sdmlib.serialization.util.JsonIdMapPOCreator());
      jsonIdMap.with(new org.sdmlib.serialization.util.SDMLibJsonIdMapCreator());
      jsonIdMap.with(new org.sdmlib.serialization.util.SDMLibJsonIdMapPOCreator());
      
      return jsonIdMap;
   }
}

