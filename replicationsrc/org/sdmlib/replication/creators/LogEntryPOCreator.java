package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LogEntryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntryPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((LogEntryPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((LogEntryPO) target).set(attrName, value);
   }
}
