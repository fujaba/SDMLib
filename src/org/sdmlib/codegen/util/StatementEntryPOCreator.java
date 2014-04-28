package org.sdmlib.codegen.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class StatementEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StatementEntryPO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((StatementEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StatementEntryPO) target).set(attrName, value);
   }
}

