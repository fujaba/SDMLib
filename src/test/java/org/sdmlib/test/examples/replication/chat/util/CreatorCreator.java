package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new ChatRootCreator());
      jsonIdMap.with(new ChatRootPOCreator());
      jsonIdMap.with(new ChatUserCreator());
      jsonIdMap.with(new ChatUserPOCreator());
      jsonIdMap.with(new ChatChannelCreator());
      jsonIdMap.with(new ChatChannelPOCreator());
      jsonIdMap.with(new ChatMsgCreator());
      jsonIdMap.with(new ChatMsgPOCreator());
      return jsonIdMap;
   }
}
