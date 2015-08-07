package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardStepCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardStepPOCreator());
     
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardWallCreator());
      jsonIdMap.withCreator(new org.sdmlib.storyboards.util.StoryboardWallPOCreator());
      jsonIdMap.withCreator(new KanbanEntryCreator());
      jsonIdMap.withCreator(new KanbanEntryPOCreator());
      jsonIdMap.withCreator(new LogEntryStoryBoardCreator());
      jsonIdMap.withCreator(new LogEntryStoryBoardPOCreator());
      jsonIdMap.withCreator(new StoryboardWallCreator());
      jsonIdMap.withCreator(new StoryboardWallPOCreator());
      jsonIdMap.withCreator(new StoryboardCreator());
      jsonIdMap.withCreator(new StoryboardPOCreator());
      jsonIdMap.withCreator(new StoryboardStepCreator());
      jsonIdMap.withCreator(new StoryboardStepPOCreator());
      return jsonIdMap;
   }
}
