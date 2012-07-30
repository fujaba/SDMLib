package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((DicePO) target).set(attrName, value);
   }
}

