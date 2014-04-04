package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class FemalePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new FemalePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((FemalePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((FemalePO) target).set(attrName, value);
   }
}
