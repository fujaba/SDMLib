package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.TaskLane;

import de.uniks.networkparser.IdMap;

public class TaskLanePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TaskLanePO(new TaskLane[]{});
      } else {
          return new TaskLanePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
