package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.KanbanEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.KanbanEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.LogEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.LogEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardWallCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardWallPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardStepCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardStepPOCreator());
     
      return jsonIdMap;
   }
}
