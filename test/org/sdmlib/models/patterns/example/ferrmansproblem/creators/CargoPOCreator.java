package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CargoPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CargoPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CargoPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CargoPO) target).set(attrName, value);
   }
}

