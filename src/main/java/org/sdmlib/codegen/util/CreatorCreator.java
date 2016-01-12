package org.sdmlib.codegen.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new LocalVarTableEntryCreator());
      jsonIdMap.with(new LocalVarTableEntryPOCreator());
      jsonIdMap.with(new StatementEntryCreator());
      jsonIdMap.with(new StatementEntryPOCreator());
      jsonIdMap.with(new SymTabEntryCreator());
      jsonIdMap.with(new SymTabEntryPOCreator());
      return jsonIdMap;
   }
}
