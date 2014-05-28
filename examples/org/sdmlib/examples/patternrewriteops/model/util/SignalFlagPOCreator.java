package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.patternrewriteops.model.SignalFlag;

public class SignalFlagPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SignalFlagPO(new SignalFlag[]{});
      } else {
          return new SignalFlagPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((SignalFlagPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SignalFlagPO) target).set(attrName, value);
   }
}

