package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class FieldPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new FieldPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((FieldPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((FieldPO) target).set(attrName, value);
   }
}

