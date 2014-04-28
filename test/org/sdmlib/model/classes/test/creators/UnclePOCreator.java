package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class UnclePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new UnclePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((UnclePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((UnclePO) target).set(attrName, value);
   }
}
