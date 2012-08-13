package org.sdmlib.codegen.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LocalVarTableEntryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LocalVarTableEntryPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LocalVarTableEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((LocalVarTableEntryPO) target).set(attrName, value);
   }
}

