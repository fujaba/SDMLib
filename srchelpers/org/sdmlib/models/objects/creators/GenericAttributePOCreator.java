package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GenericAttributePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericAttributePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericAttributePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericAttributePO) target).set(attrName, value);
   }
}

