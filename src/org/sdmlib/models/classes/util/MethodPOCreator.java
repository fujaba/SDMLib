package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class MethodPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MethodPO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((MethodPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((MethodPO) target).set(attrName, value);
   }
}

