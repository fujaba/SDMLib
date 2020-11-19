package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.storyboards.LogEntry;

import de.uniks.networkparser.IdMap;

public class LogEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LogEntryPO(new LogEntry[]{});
      } else {
          return new LogEntryPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
}
