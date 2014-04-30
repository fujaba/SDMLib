package org.sdmlib.examples.helloworld.model.creators;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class NodePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new NodePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((NodePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((NodePO) target).set(attrName, value);
   }
}
