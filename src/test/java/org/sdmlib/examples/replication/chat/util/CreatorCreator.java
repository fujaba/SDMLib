package org.sdmlib.examples.replication.chat.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatRootCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatRootPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatUserCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatUserPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatChannelCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatChannelPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatMsgCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.replication.chat.util.ChatMsgPOCreator());

      return jsonIdMap;
   }
}
