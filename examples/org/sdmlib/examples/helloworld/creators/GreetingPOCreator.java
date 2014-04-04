package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GreetingPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GreetingPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((PatternObject) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternObject) target).set(attrName, value);
   }
}
