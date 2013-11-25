package org.sdmlib.models.pattern.creators;


public class OptionalSubPatternPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new OptionalSubPatternPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((OptionalSubPatternPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((OptionalSubPatternPO) target).set(attrName, value);
   }
}

