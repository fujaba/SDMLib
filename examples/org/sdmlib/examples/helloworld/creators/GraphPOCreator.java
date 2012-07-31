package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GraphPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GraphPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GraphPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GraphPO) target).set(attrName, value);
   }
}

