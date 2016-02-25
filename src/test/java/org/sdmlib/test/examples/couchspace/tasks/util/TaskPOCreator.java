package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.couchspace.tasks.Task;

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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
}
