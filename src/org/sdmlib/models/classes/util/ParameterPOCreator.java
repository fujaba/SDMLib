package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class ParameterPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ParameterPO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((ParameterPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ParameterPO) target).set(attrName, value);
   }
}
