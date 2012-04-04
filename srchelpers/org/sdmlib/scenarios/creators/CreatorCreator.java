package org.sdmlib.scenarios.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.KanbanEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.LogEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.KanbanEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.LogEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.KanbanEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.LogEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.KanbanEntryCreator());
      jsonIdMap.addCreator(new org.sdmlib.scenarios.creators.LogEntryCreator());
      return jsonIdMap;
   }
}




