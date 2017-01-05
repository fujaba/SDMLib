package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
}
