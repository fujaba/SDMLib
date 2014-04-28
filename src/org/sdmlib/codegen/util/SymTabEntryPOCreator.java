package org.sdmlib.codegen.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class SymTabEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SymTabEntryPO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((SymTabEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((SymTabEntryPO) target).set(attrName, value);
   }
}

