package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.TestChatMessageFlow;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class TestChatMessageFlowPO extends PatternObject<TestChatMessageFlowPO, TestChatMessageFlow>
{
   public TestChatMessageFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TestChatMessageFlowSet matches = new TestChatMessageFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TestChatMessageFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((TestChatMessageFlow) getCurrentMatch()).run();
      }
   }

   
   //==========================================================================
   
   public Object getTaskNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TestChatMessageFlow) getCurrentMatch()).getTaskNames();
      }
      return null;
   }

   public TestChatMessageFlowPO hasMsg(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TestChatMessageFlow.PROPERTY_MSG)
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
         return ((TestChatMessageFlow) getCurrentMatch()).getMsg();
      }
      return null;
   }
   
   public TestChatMessageFlowPO withMsg(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TestChatMessageFlow) getCurrentMatch()).setMsg(value);
      }
      return this;
   }
   
   public TestChatMessageFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TestChatMessageFlow.PROPERTY_TASKNO)
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
         return ((TestChatMessageFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public TestChatMessageFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TestChatMessageFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
}

