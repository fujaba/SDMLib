package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class UpdateAdamFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new UpdateAdamFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((UpdateAdamFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((UpdateAdamFlowPO) target).set(attrName, value);
   }
}

