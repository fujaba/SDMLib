package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SignalFlagPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SignalFlagPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((SignalFlagPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((SignalFlagPO) target).set(attrName, value);
   }
}
