package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class P2PNetworkLoginFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new P2PNetworkLoginFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((P2PNetworkLoginFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((P2PNetworkLoginFlowPO) target).set(attrName, value);
   }
}

