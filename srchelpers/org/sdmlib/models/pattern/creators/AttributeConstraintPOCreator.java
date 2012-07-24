package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class AttributeConstraintPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AttributeConstraintPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AttributeConstraintPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((AttributeConstraintPO) target).set(attrName, value);
   }
}

