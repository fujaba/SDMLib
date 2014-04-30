package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class LudoColorPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LudoColorPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoColorPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoColorPO) target).set(attrName, value);
   }
}

