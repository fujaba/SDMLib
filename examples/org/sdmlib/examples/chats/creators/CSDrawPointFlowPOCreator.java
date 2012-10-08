package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CSDrawPointFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CSDrawPointFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSDrawPointFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSDrawPointFlowPO) target).set(attrName, value);
   }
}

