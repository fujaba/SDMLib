package org.sdmlib.test.examples.modelspace.chat.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatGroupCreator());
      jsonIdMap.withCreator(new MSChatGroupPOCreator());
      jsonIdMap.withCreator(new MSChatMemberCreator());
      jsonIdMap.withCreator(new MSChatMemberPOCreator());
      jsonIdMap.withCreator(new MSChatChannelDescriptionCreator());
      jsonIdMap.withCreator(new MSChatChannelDescriptionPOCreator());
      jsonIdMap.withCreator(new MSChatChannelCreator());
      jsonIdMap.withCreator(new MSChatChannelPOCreator());
      jsonIdMap.withCreator(new MSChatMsgCreator());
      jsonIdMap.withCreator(new MSChatMsgPOCreator());
      jsonIdMap.withCreator(new MSChatGroupCreator());
      jsonIdMap.withCreator(new MSChatGroupPOCreator());
      jsonIdMap.withCreator(new MSChatMemberCreator());
      jsonIdMap.withCreator(new MSChatMemberPOCreator());
      jsonIdMap.withCreator(new MSChatChannelDescriptionCreator());
      jsonIdMap.withCreator(new MSChatChannelDescriptionPOCreator());
      return jsonIdMap;
   }
}
