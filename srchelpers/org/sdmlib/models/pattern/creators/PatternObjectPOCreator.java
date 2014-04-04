package org.sdmlib.models.pattern.creators;

public class PatternObjectPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObjectPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((PatternObjectPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternObjectPO) target).set(attrName, value);
   }
}
