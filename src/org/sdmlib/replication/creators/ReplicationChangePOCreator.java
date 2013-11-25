package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReplicationChangePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationChangePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationChangePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReplicationChangePO) target).set(attrName, value);
   }
}

