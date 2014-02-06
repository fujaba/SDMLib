package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChangeHistoryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChangeHistoryPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChangeHistoryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChangeHistoryPO) target).set(attrName, value);
   }
}

