package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class P2PChatMessageFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new P2PChatMessageFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((P2PChatMessageFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((P2PChatMessageFlowPO) target).set(attrName, value);
   }
}

