package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class AttributePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new AttributePO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((AttributePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((AttributePO) target).set(attrName, value);
   }
}

