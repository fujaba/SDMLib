package org.sdmlib.model.test.methods.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PlacePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PlacePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PlacePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PlacePO) target).set(attrName, value);
   }
}

