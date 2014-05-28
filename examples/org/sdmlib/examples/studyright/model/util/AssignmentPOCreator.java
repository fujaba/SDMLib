package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Assignment;

public class AssignmentPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AssignmentPO(new Assignment[]{});
      } else {
          return new AssignmentPO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((AssignmentPO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((AssignmentPO) target).set(attrName, value);
   }
}

