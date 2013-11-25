package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class DataPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new DataPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DataPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DataPO) target).set(attrName, value);
   }
}

