package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReplicationChannelPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationChannelPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationChannelPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((ReplicationChannelPO) target).set(attrName, value);
   }
}
