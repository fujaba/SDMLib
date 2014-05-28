package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.patternrewriteops.model.Train;

public class TrainPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TrainPO(new Train[]{});
      } else {
          return new TrainPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((TrainPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TrainPO) target).set(attrName, value);
   }
}

