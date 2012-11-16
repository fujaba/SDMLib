package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TestChatMessageFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TestChatMessageFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TestChatMessageFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TestChatMessageFlowPO) target).set(attrName, value);
   }
}

