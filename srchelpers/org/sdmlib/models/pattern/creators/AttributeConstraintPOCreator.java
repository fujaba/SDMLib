package org.sdmlib.models.pattern.creators;


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

