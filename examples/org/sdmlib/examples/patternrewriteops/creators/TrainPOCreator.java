package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TrainPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TrainPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((TrainPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((TrainPO) target).set(attrName, value);
   }
}
