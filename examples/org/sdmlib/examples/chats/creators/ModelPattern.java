package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.examples.chats.ClearDrawingFlow;
import org.sdmlib.examples.chats.DrawPointFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxyPO;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.chats.creators.PeerToPeerChatPO;
import org.sdmlib.examples.chats.creators.ChatMessageFlowPO;
import org.sdmlib.examples.chats.creators.DrawPointFlowPO;
import org.sdmlib.examples.chats.creators.ClearDrawingFlowPO;
import org.sdmlib.examples.chats.creators.PeerToPeerChatArgsPO;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public PeerToPeerChatPO hasElementPeerToPeerChatPO()
   {
      PeerToPeerChatPO value = new PeerToPeerChatPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerToPeerChatPO hasElementPeerToPeerChatPO(PeerToPeerChat hostGraphObject)
   {
      PeerToPeerChatPO value = new PeerToPeerChatPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ChatMessageFlowPO hasElementChatMessageFlowPO()
   {
      ChatMessageFlowPO value = new ChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChatMessageFlowPO hasElementChatMessageFlowPO(ChatMessageFlow hostGraphObject)
   {
      ChatMessageFlowPO value = new ChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TaskFlowPO hasElementTaskFlowPO()
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TaskFlowPO hasElementTaskFlowPO(TaskFlow hostGraphObject)
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public DrawPointFlowPO hasElementDrawPointFlowPO()
   {
      DrawPointFlowPO value = new DrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public DrawPointFlowPO hasElementDrawPointFlowPO(DrawPointFlow hostGraphObject)
   {
      DrawPointFlowPO value = new DrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ClearDrawingFlowPO hasElementClearDrawingFlowPO()
   {
      ClearDrawingFlowPO value = new ClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ClearDrawingFlowPO hasElementClearDrawingFlowPO(ClearDrawingFlow hostGraphObject)
   {
      ClearDrawingFlowPO value = new ClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PeerToPeerChatArgsPO hasElementPeerToPeerChatArgsPO()
   {
      PeerToPeerChatArgsPO value = new PeerToPeerChatArgsPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerToPeerChatArgsPO hasElementPeerToPeerChatArgsPO(PeerToPeerChatArgs hostGraphObject)
   {
      PeerToPeerChatArgsPO value = new PeerToPeerChatArgsPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PeerProxyPO hasElementPeerProxyPO()
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerProxyPO hasElementPeerProxyPO(PeerProxy hostGraphObject)
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}







