package org.sdmlib.logger.util;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class TaskFlowPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new TaskFlowPO(new TaskFlow[]{});
      } else {
         return new TaskFlowPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

