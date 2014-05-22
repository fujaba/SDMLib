package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class NodePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new NodePO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((NodePO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((NodePO) target).set(attrName, value);
   }
}

