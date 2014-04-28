package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class RolePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new RolePO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((RolePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((RolePO) target).set(attrName, value);
   }
}

