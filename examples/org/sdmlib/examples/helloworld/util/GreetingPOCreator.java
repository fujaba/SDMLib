package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class GreetingPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GreetingPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GreetingPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GreetingPO) target).set(attrName, value);
   }
}

