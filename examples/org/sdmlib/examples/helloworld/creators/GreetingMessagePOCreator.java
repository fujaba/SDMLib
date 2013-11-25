package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GreetingMessagePO) target).set(attrName, value);
   }
}

