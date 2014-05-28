package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Professor;

public class ProfessorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ProfessorPO(new Professor[]{});
      } else {
          return new ProfessorPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((ProfessorPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ProfessorPO) target).set(attrName, value);
   }
}

