package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CSChatMessageFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CSChatMessageFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSChatMessageFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSChatMessageFlowPO) target).set(attrName, value);
   }
}

