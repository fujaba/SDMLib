package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class UniversityPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new UniversityPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((UniversityPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((UniversityPO) target).set(attrName, value);
   }
}

