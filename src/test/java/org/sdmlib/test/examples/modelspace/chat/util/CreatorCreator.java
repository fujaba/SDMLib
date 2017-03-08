package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
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
