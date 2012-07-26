package org.sdmlib.model.test.interfaces.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StudentPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StudentPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StudentPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StudentPO) target).set(attrName, value);
   }
}

