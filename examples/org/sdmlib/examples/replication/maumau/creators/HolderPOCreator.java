package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class HolderPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new HolderPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((HolderPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((HolderPO) target).set(attrName, value);
   }
}

