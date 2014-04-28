package org.sdmlib.models.objects.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class GenericObjectPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericObjectPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericObjectPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericObjectPO) target).set(attrName, value);
   }
}

