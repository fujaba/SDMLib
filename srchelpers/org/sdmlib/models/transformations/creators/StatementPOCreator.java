package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StatementPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StatementPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StatementPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StatementPO) target).set(attrName, value);
   }
}
