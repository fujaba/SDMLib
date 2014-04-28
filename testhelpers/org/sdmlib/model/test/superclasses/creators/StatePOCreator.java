package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StatePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((StatePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StatePO) target).set(attrName, value);
   }
}
