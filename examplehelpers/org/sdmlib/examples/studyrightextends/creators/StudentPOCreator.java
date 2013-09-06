package org.sdmlib.examples.studyrightextends.creators;

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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StudentPO) target).set(attrName, value);
   }
}

