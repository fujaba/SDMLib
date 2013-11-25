package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class IntegerPropertyPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new IntegerPropertyPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((IntegerPropertyPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((IntegerPropertyPO) target).set(attrName, value);
   }
}

