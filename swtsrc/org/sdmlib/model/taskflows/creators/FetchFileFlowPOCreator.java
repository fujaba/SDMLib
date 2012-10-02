package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class FetchFileFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new FetchFileFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((FetchFileFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((FetchFileFlowPO) target).set(attrName, value);
   }
}

