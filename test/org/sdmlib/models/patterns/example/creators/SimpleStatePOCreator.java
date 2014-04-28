package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SimpleStatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SimpleStatePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((SimpleStatePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((SimpleStatePO) target).set(attrName, value);
   }
}
