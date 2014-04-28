package org.sdmlib.models.pattern.util;


public class PatternPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PatternPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternPO) target).set(attrName, value);
   }
}

