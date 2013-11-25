package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReachabilityGraphPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReachabilityGraphPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReachabilityGraphPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReachabilityGraphPO) target).set(attrName, value);
   }
}

