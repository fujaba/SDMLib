package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.replication.chat.ChatChannel;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
