package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TaskPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TaskPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TaskPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TaskPO) target).set(attrName, value);
   }
}

