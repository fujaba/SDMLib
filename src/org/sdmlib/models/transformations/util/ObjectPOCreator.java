package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class ObjectPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ObjectPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ObjectPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ObjectPO) target).set(attrName, value);
   }
}

