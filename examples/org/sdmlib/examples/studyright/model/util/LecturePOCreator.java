package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Lecture;

public class LecturePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LecturePO(new Lecture[]{});
      } else {
          return new LecturePO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((LecturePO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LecturePO) target).set(attrName, value);
   }
}

