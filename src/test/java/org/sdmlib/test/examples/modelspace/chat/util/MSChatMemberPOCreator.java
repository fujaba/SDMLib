package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

public class MSChatMemberPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MSChatMemberPO(new MSChatMember[]{});
      } else {
          return new MSChatMemberPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
