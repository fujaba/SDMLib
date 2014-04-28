package org.sdmlib.models.pattern.util;


public class ReachableStatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReachableStatePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReachableStatePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReachableStatePO) target).set(attrName, value);
   }
}

