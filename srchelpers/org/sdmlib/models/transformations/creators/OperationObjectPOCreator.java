package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class OperationObjectPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new OperationObjectPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((OperationObjectPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((OperationObjectPO) target).set(attrName, value);
   }
}

