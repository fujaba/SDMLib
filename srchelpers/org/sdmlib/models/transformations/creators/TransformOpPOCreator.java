package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TransformOpPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TransformOpPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TransformOpPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((TransformOpPO) target).set(attrName, value);
   }
}

