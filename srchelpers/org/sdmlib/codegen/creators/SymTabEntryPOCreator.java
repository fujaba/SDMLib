package org.sdmlib.codegen.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SymTabEntryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SymTabEntryPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SymTabEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((SymTabEntryPO) target).set(attrName, value);
   }
}

