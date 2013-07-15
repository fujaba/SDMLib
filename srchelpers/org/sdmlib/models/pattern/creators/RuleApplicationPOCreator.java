package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class RuleApplicationPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new RuleApplicationPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((RuleApplicationPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((RuleApplicationPO) target).set(attrName, value);
   }
}

