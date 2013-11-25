package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SDMTimerPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SDMTimerPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SDMTimerPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SDMTimerPO) target).set(attrName, value);
   }
}

