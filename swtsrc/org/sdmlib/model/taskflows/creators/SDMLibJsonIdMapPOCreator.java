package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SDMLibJsonIdMapPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SDMLibJsonIdMapPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SDMLibJsonIdMapPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SDMLibJsonIdMapPO) target).set(attrName, value);
   }
}

