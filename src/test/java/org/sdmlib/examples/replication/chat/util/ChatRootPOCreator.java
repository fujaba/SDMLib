package org.sdmlib.examples.replication.chat.util;

import org.sdmlib.examples.replication.chat.ChatRoot;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class ChatRootPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChatRootPO(new ChatRoot[]{});
      } else {
          return new ChatRootPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
