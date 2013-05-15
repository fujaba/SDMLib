package org.sdmlib.scenarios.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.scenarios.creators.KanbanEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.scenarios.creators.LogEntryCreator());
      return jsonIdMap;
   }
}






























