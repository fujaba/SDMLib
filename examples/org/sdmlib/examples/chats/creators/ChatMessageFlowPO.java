package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.examples.chats.creators.ChatMessageFlowSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.model.taskflows.PeerProxy;

public class ChatMessageFlowPO extends PatternObject<ChatMessageFlowPO, ChatMessageFlow>
{
   public ChatMessageFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatMessageFlowSet matches = new ChatMessageFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatMessageFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ChatMessageFlowPO hasMsg(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMessageFlow.PROPERTY_MSG)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getMsg()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMessageFlow) getCurrentMatch()).getMsg();
      }
      return null;
   }
   
   public ChatMessageFlowPO hasGui(PeerToPeerChat value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMessageFlow.PROPERTY_GUI)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerToPeerChat getGui()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMessageFlow) getCurrentMatch()).getGui();
      }
      return null;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((ChatMessageFlow) getCurrentMatch()).run();
      }
   }

   public ChatMessageFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMessageFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMessageFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
}




