package org.sdmlib.model.taskflows.util;

import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class SocketThreadPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SocketThreadPO(new SocketThread[]{});
      } else {
          return new SocketThreadPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
