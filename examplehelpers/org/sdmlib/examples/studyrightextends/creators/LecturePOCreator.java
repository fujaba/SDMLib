package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LecturePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LecturePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((LecturePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((LecturePO) target).set(attrName, value);
   }
}
