package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ContinentPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ContinentPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ContinentPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ContinentPO) target).set(attrName, value);
   }
}

