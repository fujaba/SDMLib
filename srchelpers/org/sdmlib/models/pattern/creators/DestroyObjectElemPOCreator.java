package org.sdmlib.models.pattern.creators;

public class DestroyObjectElemPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new DestroyObjectElemPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((DestroyObjectElemPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((DestroyObjectElemPO) target).set(attrName, value);
   }
}
