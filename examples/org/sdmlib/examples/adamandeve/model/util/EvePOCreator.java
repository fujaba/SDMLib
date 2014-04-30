package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class EvePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new EvePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((EvePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((EvePO) target).set(attrName, value);
   }
}

