package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TownPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TownPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TownPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((TownPO) target).set(attrName, value);
   }
}

