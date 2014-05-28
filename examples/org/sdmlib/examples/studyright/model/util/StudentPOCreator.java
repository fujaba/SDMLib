package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Student;

public class StudentPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StudentPO(new Student[]{});
      } else {
          return new StudentPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((StudentPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StudentPO) target).set(attrName, value);
   }
}

