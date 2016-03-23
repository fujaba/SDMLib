package org.sdmlib.test.examples.modelspace.chat.util;

import de.uniks.networkparser.IdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new MSChatChannelCreator());
      jsonIdMap.with(new MSChatChannelPOCreator());
      jsonIdMap.with(new MSChatMsgCreator());
      jsonIdMap.with(new MSChatMsgPOCreator());
      jsonIdMap.with(new MSChatGroupCreator());
      jsonIdMap.with(new MSChatGroupPOCreator());
      jsonIdMap.with(new MSChatMemberCreator());
      jsonIdMap.with(new MSChatMemberPOCreator());
       jsonIdMap.with(new MSChatChannelDescriptionCreator());
      jsonIdMap.with(new MSChatChannelDescriptionPOCreator());
      return jsonIdMap;
   }
}
