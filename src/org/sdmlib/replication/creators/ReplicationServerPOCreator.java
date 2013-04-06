package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReplicationServerPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationServerPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationServerPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReplicationServerPO) target).set(attrName, value);
   }
}

