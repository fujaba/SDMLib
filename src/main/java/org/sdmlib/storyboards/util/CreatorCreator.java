package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardPOCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardStepCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardStepPOCreator());
     
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardWallCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardWallPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      jsonIdMap.with(new StoryboardWallCreator());
      jsonIdMap.with(new StoryboardWallPOCreator());
      jsonIdMap.with(new StoryboardCreator());
      jsonIdMap.with(new StoryboardPOCreator());
      jsonIdMap.with(new StoryboardStepCreator());
      jsonIdMap.with(new StoryboardStepPOCreator());
      return jsonIdMap;
   }
}
