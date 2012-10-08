package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ClientLoginFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ClientLoginFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClientLoginFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ClientLoginFlowPO) target).set(attrName, value);
   }
}

