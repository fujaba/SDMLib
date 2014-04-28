package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class DatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new DatePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((DatePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((DatePO) target).set(attrName, value);
   }
}
