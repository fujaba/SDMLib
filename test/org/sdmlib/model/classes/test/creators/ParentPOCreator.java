package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ParentPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ParentPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ParentPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((ParentPO) target).set(attrName, value);
   }
}
