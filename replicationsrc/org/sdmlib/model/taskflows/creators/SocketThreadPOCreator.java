package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SocketThreadPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SocketThreadPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((SocketThreadPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((SocketThreadPO) target).set(attrName, value);
   }
}
