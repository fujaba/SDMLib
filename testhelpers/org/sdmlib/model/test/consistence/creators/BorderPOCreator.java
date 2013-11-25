package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class BorderPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new BorderPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BorderPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((BorderPO) target).set(attrName, value);
   }
}

