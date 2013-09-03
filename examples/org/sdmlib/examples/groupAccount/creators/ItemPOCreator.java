package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ItemPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ItemPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ItemPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ItemPO) target).set(attrName, value);
   }
}

