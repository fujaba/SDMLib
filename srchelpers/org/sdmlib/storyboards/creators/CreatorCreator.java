package org.sdmlib.storyboards.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.storyboards.creators.KanbanEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.creators.LogEntryCreator());
      return jsonIdMap;
   }
}






























