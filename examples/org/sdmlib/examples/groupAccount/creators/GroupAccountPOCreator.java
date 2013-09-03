package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GroupAccountPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GroupAccountPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GroupAccountPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GroupAccountPO) target).set(attrName, value);
   }
}

