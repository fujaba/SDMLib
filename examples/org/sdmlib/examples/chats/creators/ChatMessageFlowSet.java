package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class ChatMessageFlowSet extends LinkedHashSet<ChatMessageFlow>
{
   public StringList getMsg()
   {
      StringList result = new StringList();
      
      for (ChatMessageFlow obj : this)
      {
         result.add(obj.getMsg());
      }
      
      return result;
   }

   public ChatMessageFlowSet withMsg(String value)
   {
      for (ChatMessageFlow obj : this)
      {
         obj.withMsg(value);
      }
      
      return this;
   }

   public PeerToPeerChatSet getGui()
   {
      PeerToPeerChatSet result = new PeerToPeerChatSet();
      
      for (ChatMessageFlow obj : this)
      {
         result.add(obj.getGui());
      }
      
      return result;
   }

   public ChatMessageFlowSet withGui(PeerToPeerChat value)
   {
      for (ChatMessageFlow obj : this)
      {
         obj.withGui(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public ChatMessageFlowSet run()
   {
      for (ChatMessageFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (ChatMessageFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public ChatMessageFlowSet withTaskNo(int value)
   {
      for (ChatMessageFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

}




