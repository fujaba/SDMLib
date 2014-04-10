package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReplicationRootPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationRootPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationRootPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReplicationRootPO) target).set(attrName, value);
   }
}

