package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChatMessageFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChatMessageFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatMessageFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatMessageFlowPO) target).set(attrName, value);
   }
}

