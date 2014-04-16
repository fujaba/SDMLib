package org.sdmlib.models.pattern.creators;


public class StringBuilderPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StringBuilderPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StringBuilderPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StringBuilderPO) target).set(attrName, value);
   }
}

