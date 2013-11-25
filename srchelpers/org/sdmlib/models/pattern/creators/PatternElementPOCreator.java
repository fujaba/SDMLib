package org.sdmlib.models.pattern.creators;


public class PatternElementPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PatternElementPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternElementPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternElementPO) target).set(attrName, value);
   }
}

