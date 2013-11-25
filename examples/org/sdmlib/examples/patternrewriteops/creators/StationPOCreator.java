package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StationPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StationPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StationPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StationPO) target).set(attrName, value);
   }
}

