package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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
   
   public ChatMessageFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMessageFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMessageFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public ChatMessageFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChatMessageFlow) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public ChatMessageFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_SUBFLOW)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getSubFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getSubFlow();
      }
      return null;
   }

   public TaskFlowPO hasParent()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public ChatMessageFlowPO hasParent(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

}






