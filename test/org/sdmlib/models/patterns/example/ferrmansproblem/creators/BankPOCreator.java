package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class BankPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new BankPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BankPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((BankPO) target).set(attrName, value);
   }
}

