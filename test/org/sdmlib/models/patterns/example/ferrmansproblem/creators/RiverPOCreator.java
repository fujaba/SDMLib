package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class RiverPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new RiverPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((RiverPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((RiverPO) target).set(attrName, value);
   }
}

