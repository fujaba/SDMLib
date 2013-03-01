package org.sdmlib.models.pattern.creators;


public class NegativeApplicationConditionPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new NegativeApplicationConditionPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((NegativeApplicationConditionPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((NegativeApplicationConditionPO) target).set(attrName, value);
   }
}

