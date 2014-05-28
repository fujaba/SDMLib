package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.examples.studyright.model.Female;

public class FemalePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FemalePO(new Female[]{});
      } else {
          return new FemalePO();
      }
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((FemalePO) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((FemalePO) target).set(attrName, value);
   }
}

