package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.TaskBoard;

import de.uniks.networkparser.IdMap;

public class TaskBoardPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TaskBoardPO(new TaskBoard[]{});
      } else {
          return new TaskBoardPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
