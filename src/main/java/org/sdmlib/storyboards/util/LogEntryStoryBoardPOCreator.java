package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryStoryBoardPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LogEntryStoryBoardPO(new LogEntryStoryBoard[]{});
      } else {
          return new LogEntryStoryBoardPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
}
