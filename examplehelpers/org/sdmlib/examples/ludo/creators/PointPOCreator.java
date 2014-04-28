package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PointPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PointPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((PointPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((PointPO) target).set(attrName, value);
   }
}
