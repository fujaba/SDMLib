package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StudyRightClassesCodeGenPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StudyRightClassesCodeGenPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StudyRightClassesCodeGenPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StudyRightClassesCodeGenPO) target).set(attrName, value);
   }
}

