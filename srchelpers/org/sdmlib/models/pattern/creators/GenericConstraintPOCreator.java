package org.sdmlib.models.pattern.creators;


public class GenericConstraintPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericConstraintPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericConstraintPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericConstraintPO) target).set(attrName, value);
   }
}

