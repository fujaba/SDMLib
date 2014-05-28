package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Male;

public class MalePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MalePO(new Male[]{});
      } else {
          return new MalePO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((MalePO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MalePO) target).set(attrName, value);
   }
}

