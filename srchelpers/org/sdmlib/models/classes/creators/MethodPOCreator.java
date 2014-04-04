package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class MethodPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new MethodPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((MethodPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((MethodPO) target).set(attrName, value);
   }
}
