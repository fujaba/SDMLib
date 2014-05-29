package org.sdmlib.codegen.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new LocalVarTableEntryPOCreator());
      jsonIdMap.withCreator(new StatementEntryCreator());
      jsonIdMap.withCreator(new StatementEntryPOCreator());
      jsonIdMap.withCreator(new SymTabEntryCreator());
      jsonIdMap.withCreator(new SymTabEntryPOCreator());
      return jsonIdMap;
   }
}
