package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class UnifyGraphsOpPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new UnifyGraphsOpPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((UnifyGraphsOpPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((UnifyGraphsOpPO) target).set(attrName, value);
   }
}

