package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ClazzPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ClazzPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClazzPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ClazzPO) target).set(attrName, value);
   }
}

