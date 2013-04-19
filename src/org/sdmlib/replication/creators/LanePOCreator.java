package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LanePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LanePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LanePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LanePO) target).set(attrName, value);
   }
}

