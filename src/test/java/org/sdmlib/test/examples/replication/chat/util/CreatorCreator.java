package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatRootCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatRootPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatUserCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatUserPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatChannelCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatChannelPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatMsgCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.replication.chat.util.ChatMsgPOCreator());

      return jsonIdMap;
   }
}
