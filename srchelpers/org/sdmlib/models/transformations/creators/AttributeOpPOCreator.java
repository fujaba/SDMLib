package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class AttributeOpPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AttributeOpPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AttributeOpPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((AttributeOpPO) target).set(attrName, value);
   }
}

