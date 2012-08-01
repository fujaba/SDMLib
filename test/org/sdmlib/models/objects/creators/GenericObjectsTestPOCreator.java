package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GenericObjectsTestPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericObjectsTestPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericObjectsTestPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericObjectsTestPO) target).set(attrName, value);
   }
}

