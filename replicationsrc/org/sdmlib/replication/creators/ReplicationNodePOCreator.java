package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReplicationNodePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationNodePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationNodePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReplicationNodePO) target).set(attrName, value);
   }
}

