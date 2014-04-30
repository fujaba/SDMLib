package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class DicePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new DicePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DicePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DicePO) target).set(attrName, value);
   }
}

