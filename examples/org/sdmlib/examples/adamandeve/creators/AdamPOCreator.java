package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class AdamPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AdamPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AdamPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((AdamPO) target).set(attrName, value);
   }
}

