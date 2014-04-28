package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChoiceTemplatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChoiceTemplatePO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ChoiceTemplatePO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((ChoiceTemplatePO) target).set(attrName, value);
   }
}
