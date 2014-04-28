package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class NoPropertiesPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new NoPropertiesPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((NoPropertiesPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((NoPropertiesPO) target).set(attrName, value);
   }
}
