package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.Task;

import de.uniks.networkparser.IdMap;

public class TaskPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TaskPO(new Task[]{});
      } else {
          return new TaskPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
