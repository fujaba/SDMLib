package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ProfessorPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ProfessorPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ProfessorPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ProfessorPO) target).set(attrName, value);
   }
}

