package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ServerSocketAcceptThreadPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ServerSocketAcceptThreadPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ServerSocketAcceptThreadPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((ServerSocketAcceptThreadPO) target).set(attrName, value);
   }
}
