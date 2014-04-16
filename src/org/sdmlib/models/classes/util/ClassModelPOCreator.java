package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ClassModelPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModelPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClassModelPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ClassModelPO) target).set(attrName, value);
   }
}

