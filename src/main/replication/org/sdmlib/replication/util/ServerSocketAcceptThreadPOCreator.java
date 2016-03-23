package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.ServerSocketAcceptThread;

import de.uniks.networkparser.IdMap;

public class ServerSocketAcceptThreadPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ServerSocketAcceptThreadPO(new ServerSocketAcceptThread[]{});
      } else {
          return new ServerSocketAcceptThreadPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
