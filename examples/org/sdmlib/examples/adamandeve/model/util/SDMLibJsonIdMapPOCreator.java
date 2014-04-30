package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class SDMLibJsonIdMapPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SDMLibJsonIdMapPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SDMLibJsonIdMapPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SDMLibJsonIdMapPO) target).set(attrName, value);
   }
}

