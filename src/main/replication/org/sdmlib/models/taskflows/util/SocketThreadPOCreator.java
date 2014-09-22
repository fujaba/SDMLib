package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.taskflows.SocketThread;

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
