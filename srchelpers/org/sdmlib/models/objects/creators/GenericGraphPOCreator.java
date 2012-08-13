package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GenericGraphPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericGraphPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericGraphPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericGraphPO) target).set(attrName, value);
   }
}

