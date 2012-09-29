package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;
   
   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()
   {
      if (creatorSet == null)
      {
         creatorSet = new LinkedHashSet<SendableEntityCreator>();
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ChatMessageFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ChatMessageFlowPOCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.DrawPointFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.DrawPointFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClearDrawingFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClearDrawingFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatArgsCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatArgsPOCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.PeerProxyCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.PeerProxyPOCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.JsonIdMapCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(getCreatorSet());

      return jsonIdMap;
   }
}







