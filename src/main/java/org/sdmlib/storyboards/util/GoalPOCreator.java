package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.storyboards.Goal;

import de.uniks.networkparser.IdMap;

public class GoalPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GoalPO(new Goal[]{});
      } else {
          return new GoalPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
}
