package org.sdmlib.codegen.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      return jsonIdMap;
   }
}



