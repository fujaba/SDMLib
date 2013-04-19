package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ExecutorPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ExecutorPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ExecutorPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ExecutorPO) target).set(attrName, value);
   }
}

