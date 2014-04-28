package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class TemplatePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TemplatePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TemplatePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TemplatePO) target).set(attrName, value);
   }
}

