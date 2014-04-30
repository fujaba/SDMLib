package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class TaskFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TaskFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TaskFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TaskFlowPO) target).set(attrName, value);
   }
}

