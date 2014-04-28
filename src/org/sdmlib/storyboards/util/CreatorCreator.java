package org.sdmlib.storyboards.util;

import java.util.LinkedHashSet;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;
   
   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()
   {
      if (creatorSet == null)
      {
         creatorSet = new LinkedHashSet<SendableEntityCreator>();
         creatorSet.add(new org.sdmlib.storyboards.util.KanbanEntryCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.KanbanEntryPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.LogEntryCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.LogEntryPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardWallCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardWallPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardStepCreator());
         creatorSet.add(new org.sdmlib.storyboards.util.StoryboardStepPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.util.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}


