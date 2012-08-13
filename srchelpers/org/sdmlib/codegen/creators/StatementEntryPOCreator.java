package org.sdmlib.codegen.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StatementEntryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StatementEntryPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StatementEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StatementEntryPO) target).set(attrName, value);
   }
}

