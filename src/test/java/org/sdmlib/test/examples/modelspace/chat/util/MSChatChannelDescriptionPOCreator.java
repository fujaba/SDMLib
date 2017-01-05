package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;

import de.uniks.networkparser.IdMap;

public class MSChatChannelDescriptionPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      } else {
          return new MSChatChannelDescriptionPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
