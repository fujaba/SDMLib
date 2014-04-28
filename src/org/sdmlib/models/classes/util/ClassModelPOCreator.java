package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class ClassModelPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModelPO();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((ClassModelPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((ClassModelPO) target).set(attrName, value);
   }
}

