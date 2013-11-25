package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class KidPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new KidPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((KidPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((KidPO) target).set(attrName, value);
   }
}

