package org.sdmlib.examples.replication.chat.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.replication.chat.ChatChannel;

public class ChatChannelPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChatChannelPO(new ChatChannel[]{});
      } else {
          return new ChatChannelPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
