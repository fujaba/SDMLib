package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LoggerPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LoggerPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LoggerPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LoggerPO) target).set(attrName, value);
   }
}

