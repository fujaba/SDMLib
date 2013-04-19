package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TaskFlowBoardPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TaskFlowBoardPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TaskFlowBoardPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TaskFlowBoardPO) target).set(attrName, value);
   }
}

