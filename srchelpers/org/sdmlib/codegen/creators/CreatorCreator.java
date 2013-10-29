package org.sdmlib.codegen.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      return jsonIdMap;
   }
}



