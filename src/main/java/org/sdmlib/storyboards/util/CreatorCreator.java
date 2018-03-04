package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardPOCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardStepCreator());
      jsonIdMap.with(new org.sdmlib.storyboards.util.StoryboardStepPOCreator());
     
      jsonIdMap.with(new KanbanEntryCreator());
      jsonIdMap.with(new KanbanEntryPOCreator());
      jsonIdMap.with(new LogEntryStoryBoardCreator());
      jsonIdMap.with(new LogEntryStoryBoardPOCreator());
      
      jsonIdMap.with(new GoalCreator());
      jsonIdMap.with(new GoalPOCreator());
      jsonIdMap.with(new LogEntryCreator());
      jsonIdMap.with(new LogEntryPOCreator());
      jsonIdMap.with(new MikadoLogCreator());
      jsonIdMap.with(new MikadoLogPOCreator());
      return jsonIdMap;
   }
}
