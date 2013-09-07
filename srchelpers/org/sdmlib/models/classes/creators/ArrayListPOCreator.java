package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ArrayListPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ArrayListPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ArrayListPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ArrayListPO) target).set(attrName, value);
   }
}

