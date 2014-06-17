package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

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

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((TaskFlowPO) target).set(attrName, value);
   }
}
