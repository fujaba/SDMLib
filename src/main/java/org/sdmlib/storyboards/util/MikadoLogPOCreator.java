package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.storyboards.MikadoLog;

import de.uniks.networkparser.IdMap;

public class MikadoLogPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MikadoLogPO(new MikadoLog[]{});
      } else {
          return new MikadoLogPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
}
