package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class DrawPointFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new DrawPointFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DrawPointFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DrawPointFlowPO) target).set(attrName, value);
   }
}

