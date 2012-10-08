package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.CSChatMessageFlow;
import org.sdmlib.examples.chats.creators.CSChatMessageFlowSet;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CSChatMessageFlowPO extends PatternObject<CSChatMessageFlowPO, CSChatMessageFlow>
{
   public CSChatMessageFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSChatMessageFlowSet matches = new CSChatMessageFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CSChatMessageFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((CSChatMessageFlow) getCurrentMatch()).run();
      }
   }

   public CSChatMessageFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSChatMessageFlow.PROPERTY_TASKNO)
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
         return ((CSChatMessageFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public CSChatMessageFlowPO hasMsg(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSChatMessageFlow.PROPERTY_MSG)
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
         return ((CSChatMessageFlow) getCurrentMatch()).getMsg();
      }
      return null;
   }
   
   public CSChatMessageFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(CSChatMessageFlow.PROPERTY_IDMAP)
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
         return ((CSChatMessageFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
}



