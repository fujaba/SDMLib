package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class GreetingMessagePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GreetingMessagePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GreetingMessagePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GreetingMessagePO) target).set(attrName, value);
   }
}

