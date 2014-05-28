package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.University;

public class UniversityPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UniversityPO(new University[]{});
      } else {
          return new UniversityPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((UniversityPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((UniversityPO) target).set(attrName, value);
   }
}

