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
         creatorSet.add(new org.sdmlib.examples.chats.creators.DrawPointFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.DrawPointFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClearDrawingFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClearDrawingFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatArgsCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.PeerToPeerChatArgsPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSChatMessageFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSChatMessageFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ChatServerCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ChatServerPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClientLoginFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.ClientLoginFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSDrawPointFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSDrawPointFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSClearDrawingFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSClearDrawingFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSVisitAllClientsFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSVisitAllClientsFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSClientTaskCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CSClientTaskPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CTDrawPointCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CTDrawPointPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CTClearDrawingCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.CTClearDrawingPOCreator());
         
         creatorSet.add(new org.sdmlib.examples.chats.creators.TestChatMessageFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.TestChatMessageFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.P2PNetworkLoginFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.P2PNetworkLoginFlowPOCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.P2PChatMessageFlowCreator());
         creatorSet.add(new org.sdmlib.examples.chats.creators.P2PChatMessageFlowPOCreator());
         creatorSet.addAll(new org.sdmlib.model.taskflows.creators.CreatorCreator().getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}


















