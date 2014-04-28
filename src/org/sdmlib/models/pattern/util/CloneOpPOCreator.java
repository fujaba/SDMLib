package org.sdmlib.models.pattern.util;


public class CloneOpPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CloneOpPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CloneOpPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CloneOpPO) target).set(attrName, value);
   }
}

