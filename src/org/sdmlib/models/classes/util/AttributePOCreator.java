package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class AttributePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AttributePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AttributePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((AttributePO) target).set(attrName, value);
   }
}

