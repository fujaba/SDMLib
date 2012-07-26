package org.sdmlib.model.classes.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ReverseClassModelTestPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ReverseClassModelTestPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReverseClassModelTestPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ReverseClassModelTestPO) target).set(attrName, value);
   }
}

