package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class EdgePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new EdgePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((EdgePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((EdgePO) target).set(attrName, value);
   }
}

