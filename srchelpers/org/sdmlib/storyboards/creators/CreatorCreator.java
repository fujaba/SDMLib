package org.sdmlib.storyboards.creators;

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
         creatorSet.add(new org.sdmlib.storyboards.creators.KanbanEntryCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.KanbanEntryPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.LogEntryCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.LogEntryPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardWallCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardWallPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardPOCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardStepCreator());
         creatorSet.add(new org.sdmlib.storyboards.creators.StoryboardStepPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
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


