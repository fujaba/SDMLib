package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.patternrewriteops.model.Station;

public class StationPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StationPO(new Station[]{});
      } else {
          return new StationPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((StationPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StationPO) target).set(attrName, value);
   }
}

