package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class LudoPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LudoPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoPO) target).set(attrName, value);
   }
}
